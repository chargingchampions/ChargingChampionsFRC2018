package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetGrabberIntake extends Command {

	public PIDSetGrabberIntake() {
		requires(Robot.grabber);
	}

	protected void initialize() {
		Robot.grabber.setSetpoint(Robot.grabberIntakeSetpoint);
		Robot.grabber.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Math.abs(Robot.grabber.getPosition() - Robot.grabber.getSetpoint()) < Robot.grabberAbsTol || Math.abs(Robot.oi.getSecondYAxis()) > 0.20 || Robot.oi.getSecondPOV() != -1;
	}

	protected void end() {
		Robot.grabber.disable();
	}

	protected void interrupted() {
		end();
	}
}
