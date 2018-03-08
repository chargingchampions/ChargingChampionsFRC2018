package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.PID.PIDSetIntake;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetScale;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightScale extends CommandGroup {
	String gameData;

	public RightScale(String givenData) {
		gameData = givenData;
		char scalePos = 0;
		try {
			scalePos = gameData.charAt(1);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (scalePos == 'L') {
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(-90, 0));
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(90, 0));
			addParallel(new PIDSetScale());
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new OpenIntakeArms());
			addSequential(new WaitCommand(1));
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new PIDSetIntake());
		} else {
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new TurnToAngle(-90, 0));
			addParallel(new PIDSetScale());
			addSequential(new DriveStraightToDistance(0, 0));
			addSequential(new OpenIntakeArms());
			addSequential(new WaitCommand(1));
			addSequential(new DriveStraightToDistance(0, 0)); // backwards
			addSequential(new PIDSetIntake());
		}
	}
}
