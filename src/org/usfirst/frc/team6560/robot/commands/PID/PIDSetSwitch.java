package org.usfirst.frc.team6560.robot.commands.PID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PIDSetSwitch extends CommandGroup {

	public PIDSetSwitch() {
		addSequential(new PIDSetGrabberSafety());
		addSequential(new PIDSetArmSwitch());
		addSequential(new PIDSetGrabberSwitch());
	}
}
