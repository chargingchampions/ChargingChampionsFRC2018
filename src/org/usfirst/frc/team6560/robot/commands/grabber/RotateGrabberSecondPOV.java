package org.usfirst.frc.team6560.robot.commands.grabber;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateGrabberSecondPOV extends Command {

    public RotateGrabberSecondPOV() {
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.grabber.disable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getSecondPOV() == 0 || Robot.oi.getSecondPOV() == 45 || Robot.oi.getSecondPOV() == 315) {
    		Robot.grabber.rotateGrabber(1.0);
    	} else if (Robot.oi.getSecondPOV() == 180 || Robot.oi.getSecondPOV() == 135 || Robot.oi.getSecondPOV() == 225) {
    		Robot.grabber.rotateGrabber(-1.0);
    	} else {
    		Robot.grabber.stopRotateGrabber();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabber.stopRotateGrabber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
