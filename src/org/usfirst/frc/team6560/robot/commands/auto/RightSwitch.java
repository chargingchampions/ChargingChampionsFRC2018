package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.PID.PIDSetIntake;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetSwitch;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightSwitch extends CommandGroup {

	String gameData;

	public RightSwitch(String givenData) {
		gameData = givenData;
		char switchPos = 0;
		try {
			switchPos = gameData.charAt(0);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (switchPos == 'L') {
			System.out.println("Going to left switch...");
			addParallel(new PIDSetSwitch());
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(-90, 0));
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(-90, 0));
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(-90, 0));
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new OpenIntakeArms());
			addSequential(new WaitCommand(1));
			addParallel(new PIDSetIntake());
			addSequential(new DriveStraightToDistance(0, 0)); //backwards
		} else {
			System.out.println("Going to right switch");
			addParallel(new PIDSetSwitch());
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(-90, 0));
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new OpenIntakeArms());
			addSequential(new WaitCommand(1));
			addParallel(new PIDSetIntake());
			addSequential(new DriveStraightToDistance(0, 0)); //backwards
		}
	}
}
