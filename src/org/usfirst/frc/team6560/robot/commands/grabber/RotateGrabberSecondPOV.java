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

	protected void initialize() {
		Robot.grabber.setSetpointRelative(0);
		Robot.grabber.enable();
	}

	protected void execute() {
		if (Robot.oi.getSecondPOV() == 0 || Robot.oi.getSecondPOV() == 45 || Robot.oi.getSecondPOV() == 315) {
			Robot.grabber.disable();
			Robot.grabber.rotateGrabber(0.5);
		} else if (Robot.oi.getSecondPOV() == 180 || Robot.oi.getSecondPOV() == 135 || Robot.oi.getSecondPOV() == 225) {
			Robot.grabber.disable();
			Robot.grabber.rotateGrabber(-0.5);
		} else {
			Robot.grabber.stopRotateGrabber();
			Robot.grabber.setSetpointRelative(0);
			Robot.grabber.enable();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.grabber.disable();
		Robot.grabber.stopRotateGrabber();
	}

	protected void interrupted() {
		end();
	}
}
