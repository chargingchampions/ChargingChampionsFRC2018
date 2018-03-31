package org.usfirst.frc.team6560.robot.commands.encoderAssistance;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseEncoderAssistance extends Command {

	public UseEncoderAssistance() {
		requires(Robot.encoderAssistance);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.armBasic.getEncoderPosition() > Robot.encoderAssistanceMax) {
			Robot.encoderAssistance.setSlower();
		} else if (Robot.armBasic.getEncoderPosition() > Robot.encoderAssistanceSafety) {
			Robot.encoderAssistance.setSlow();
		} else {
			Robot.encoderAssistance.setNormal();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.encoderAssistance.setNormal();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
