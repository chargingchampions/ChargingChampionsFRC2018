package org.usfirst.frc.team6560.robot.commands.arm;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateArmWithJoystick extends Command {

	public RotateArmWithJoystick() {
		requires(Robot.arm);
	}

	protected void initialize() {
		Robot.arm.disable();
	}

	protected void execute() {
		if (Robot.oi.getSecondYAxis() > 0) {
			// && !Robot.arm.halleffect.get()))
			Robot.arm.rotate(Robot.oi.getSecondYAxis()*Robot.encoderAssistance.armScalar);
		} else if ((Robot.oi.getSecondYAxis() < 0)) {
			Robot.arm.rotate(0.8*Robot.oi.getSecondYAxis()/(Robot.encoderAssistance.armScalar+0.25));
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.arm.rotate(0);
	}

	protected void interrupted() {
		end();
	}
}
