package org.usfirst.frc.team6560.robot.commands.arm;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateArmWithJoystick extends Command {

	public RotateArmWithJoystick() {
		requires(Robot.armBasic);
	}

	protected void initialize() {
	}

	protected void execute() {
		if (Robot.oi.getSecondYAxis() > 0) {
			// && !Robot.arm.halleffect.get()))
			Robot.armBasic.rotate(Robot.oi.getSecondYAxis()*Robot.encoderAssistance.armScalar);
		} else if ((Robot.oi.getSecondYAxis() < 0)) {
			Robot.armBasic.rotate(0.6*Robot.oi.getSecondYAxis());
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.armBasic.rotate(0);
	}

	protected void interrupted() {
		end();
	}
}
