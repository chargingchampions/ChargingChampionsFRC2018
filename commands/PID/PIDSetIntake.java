package org.usfirst.frc.team6560.robot.commands.PID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSetIntake extends CommandGroup {

	public PIDSetIntake() {
		addSequential(new PIDSetGrabberSafety());
		addSequential(new PIDSetArmIntake());
		addSequential(new PIDSetGrabberIntake());
	}
}
