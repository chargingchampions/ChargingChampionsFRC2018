package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSetScale extends CommandGroup {

	public PIDSetScale() {
		if (Math.abs(Robot.arm.getPosition() - Robot.armScaleSetpoint) > Robot.armAbsTol) {
			addSequential(new PIDSetGrabberSafety());
		}
		
		if (Robot.arm.getPosition() < Robot.armHighSafetySetpoint) {
			addSequential(new PIDSetArmHighSafety());
		}
		
		addParallel(new PIDSetArmScale());
		addSequential(new PIDSetGrabberScale());
	}
	
	protected boolean isFinished() {
		return Math.abs(Robot.oi.getSecondYAxis()) > 0.20 || Robot.oi.getSecondPOV() != -1;
	}
}
