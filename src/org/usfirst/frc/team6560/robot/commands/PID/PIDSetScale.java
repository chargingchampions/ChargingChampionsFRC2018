package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSetScale extends CommandGroup {

	public PIDSetScale() {
		addSequential(new PIDSetGrabberSafety());
		addSequential(new PIDSetArmScale());
		addSequential(new PIDSetGrabberScale());
	}
	
	protected boolean isFinished() {
		return Math.abs(Robot.oi.getSecondYAxis()) > 0.20 || Robot.oi.getSecondPOV() != -1;
	}
}
