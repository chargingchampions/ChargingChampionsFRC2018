package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetGrabberSwitch extends Command {

	public PIDSetGrabberSwitch() {
		requires(Robot.grabber);
	}

	protected void initialize() {
		Robot.grabber.setSetpoint(Robot.grabberSwitchSetpoint);
		Robot.grabber.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Math.abs(Robot.grabber.getPosition() - Robot.grabber.getSetpoint()) < Robot.grabberAbsTol;
	}

	protected void end() {
		Robot.grabber.disable();
	}

	protected void interrupted() {
		end();
	}
}
