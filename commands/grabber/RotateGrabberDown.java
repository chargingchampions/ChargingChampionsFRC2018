package org.usfirst.frc.team6560.robot.commands.grabber;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateGrabberDown extends Command {

	public RotateGrabberDown() {
		requires(Robot.grabber);
	}

	protected void initialize() {
		Robot.grabber.disable();
		Robot.grabber.rotateGrabber(-1.0);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.grabber.stopRotateGrabber();
	}

	protected void interrupted() {
		end();
	}
}
