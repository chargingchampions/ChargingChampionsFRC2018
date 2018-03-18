package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightTime;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngleTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftTimeTime extends CommandGroup {

	public LeftTimeTime(String givenData) {
		
		char switchPos = 0;
		char scalePos = 0;
		try {
			switchPos = givenData.charAt(0);
			scalePos = givenData.charAt(1);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (switchPos == 'L') {
			System.out.println("Going to left switch from left station");
			//addParallel(new RotateArmTime(1.5, 0.7));
			//addParallel(new RotateGrabberTime(0.2, 0.7));
			addSequential(new DriveStraightTime(2.3, 0.7));
			addSequential(new WaitCommand(0.5));
			//addParallel(new RotateGrabberTime(0.5, 0.7));
			addSequential(new TurnToAngleTime(1.5, 0.7));
			addSequential(new WaitCommand(0.5));
			addSequential(new DriveStraightTime(0.8, 0.7));
			//addSequential(new OpenIntakeArms(0.2));
			//addSequential(new DriveStraightTime(0.5, -0.7));
			//addSequential(new TurnToAngle(-20, 0.2));
		} else if (scalePos == 'L'){
			System.out.println("I guess the game just hates us...");
		} else {
			System.out.println("I guess the game just hates us...");
		}
	}
}
