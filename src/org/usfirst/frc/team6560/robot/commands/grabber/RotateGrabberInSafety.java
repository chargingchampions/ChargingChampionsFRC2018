package org.usfirst.frc.team6560.robot.commands.grabber;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateGrabberInSafety extends Command {

    public RotateGrabberInSafety() {
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.grabber.rotateGrabber(-0.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.grabber.getEncoderPosition()<Robot.encoderGrabberSafety;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabber.rotateGrabber(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
