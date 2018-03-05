package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetArmIntake extends Command {

	public PIDSetArmIntake() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.setSetpoint(Robot.armIntakeSetpoint);
		Robot.arm.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Math.abs(Robot.arm.getPosition() - Robot.arm.getSetpoint()) < Robot.armAbsTol;
	}

	protected void end() {
		Robot.arm.disable();
	}

	protected void interrupted() {
		end();
	}
}
