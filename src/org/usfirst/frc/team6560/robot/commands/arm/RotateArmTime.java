package org.usfirst.frc.team6560.robot.commands.arm;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateArmTime extends Command {

	Timer shutdownTimer = new Timer();
	double time;
	double speed;
	
	/**
	 * 
	 * @param timeIn
	 * @param speedIn positive is up, negative is down
	 */
    public RotateArmTime(double timeIn, double speedIn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.armBasic);
    	time = timeIn;
    	speed = speedIn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shutdownTimer.reset();
    	shutdownTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.armBasic.rotate(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shutdownTimer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.armBasic.rotate(0);
    	shutdownTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
