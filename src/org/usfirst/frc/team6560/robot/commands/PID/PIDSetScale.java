package org.usfirst.frc.team6560.robot.commands.PID;

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
}
