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
	
    public RotateArmTime(double timeIn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    	time = timeIn;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shutdownTimer.reset();
    	shutdownTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.rotate(-0.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shutdownTimer.get() >= time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.stopRotate();
    	shutdownTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
