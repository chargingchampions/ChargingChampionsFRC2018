package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetArmHighSafety extends Command {

	public PIDSetArmHighSafety() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.setSetpoint(Robot.armHighSafetySetpoint);
		Robot.arm.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Math.abs(Robot.arm.getPosition() - Robot.arm.getSetpoint()) < Robot.armAbsTol || Math.abs(Robot.oi.getSecondYAxis()) > 0.20 || Robot.oi.getSecondPOV() != -1;
	}

	protected void end() {
		Robot.arm.disable();
	}

	protected void interrupted() {
		end();
	}
}
