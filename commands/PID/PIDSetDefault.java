package org.usfirst.frc.team6560.robot.commands.PID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSetDefault extends CommandGroup {

	public PIDSetDefault() {

		addSequential(new PIDSetGrabberSafety());
		addSequential(new PIDSetArmIntake());
	}
}
