package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSetIntake extends CommandGroup {

	public PIDSetIntake() {
		if (Math.abs(Robot.arm.getPosition() - Robot.armIntakeSetpoint) > Robot.armAbsTol) {
			addSequential(new PIDSetGrabberSafety());
		}
		
		addSequential(new PIDSetArmIntake());
		addSequential(new PIDSetGrabberIntake());
	}
	
	protected boolean isFinished() {
		return Math.abs(Robot.oi.getSecondYAxis()) > 0.20 || Robot.oi.getSecondPOV() != -1;
	}
}
