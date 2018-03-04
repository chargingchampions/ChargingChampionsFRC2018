package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.PID.PIDSetIntake;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetSwitch;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightRight extends CommandGroup {

	public RightRight() {
		addParallel(new PIDSetSwitch());
		addSequential(new DriveStraightToDistance(120, 0.8));
		addSequential(new TurnToAngle(90, 0.7));
		addSequential(new DriveStraightToDistance(16, 0.8));
		addSequential(new OpenIntakeArms(0.5));
		addSequential(new DriveStraightToDistance(-16, 0.8));
		addParallel(new PIDSetIntake());
		addSequential(new TurnToAngle(-90, 0.7));
		addSequential(new DriveStraightToDistance(30, 0.8));
		addSequential(new TurnToAngle(90, 0.7));
		addSequential(new DriveStraightToDistance(24, 0.8));
		addParallel(new OpenIntakeArms(2));
		addSequential(new DriveStraightToDistance(-20, 0.8));
		addSequential(new PIDSetSwitch());
		addSequential(new OpenIntakeArms(0.5));
	}
}
