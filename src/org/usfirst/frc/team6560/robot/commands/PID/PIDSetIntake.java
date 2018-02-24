package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetIntake extends Command {

    public PIDSetIntake() {
        requires(Robot.arm);
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.enable();
    	Robot.grabber.enable();
    	Robot.arm.setSetpoint(0);
    	Timer.delay(3);
    	Robot.grabber.setSetpoint(3000);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.arm.disable();
    	Robot.grabber.disable();
    }
}
