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
public class LeftEncoderPID extends CommandGroup {

	public LeftEncoderPID(String givenData) {
		
		char switchPos = 0;
		char scalePos = 0;
		try {
			switchPos = givenData.charAt(0);
			scalePos = givenData.charAt(1);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (switchPos == 'L') {
			/*
			 * addParallel(new PIDSetSwitch()); //TODO: fix safety value addSequential(new
			 * DriveStraightToDistance(128, 0.8)); addSequential(new TurnToAngle(90, 1.0));
			 * addSequential(new DriveStraightToDistance(6, 0.8)); addSequential(new
			 * ShootCube()); addParallel(new PIDSetIntake()); addSequential(new
			 * DriveStraightToDistance(-16, 0.8));
			 */
			System.out.println("Going to left switch from left station");
			addParallel(new PIDSetSwitch());
			addSequential(new DriveStraightToDistance(20, 0.7));
			addSequential(new WaitCommand(2));
			addSequential(new TurnToAngle(-25, 0.7));
			addSequential(new DriveStraightToDistance(12, 0.7));
			addSequential(new OpenIntakeArms());
			addSequential(new WaitCommand(1));
			addParallel(new PIDSetIntake());
			addSequential(new DriveStraightToDistance(-12, 0.7)); //backwards
		} else if (scalePos == 'L'){
			addSequential(new DriveStraightToDistance(200, 0.8));
		} else {
			addSequential(new DriveStraightToDistance(200, 0.8));
			System.out.println("I guess the game just hates us...");
		}
	}
}
