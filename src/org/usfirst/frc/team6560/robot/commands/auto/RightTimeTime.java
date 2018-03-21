package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.arm.RotateArmTime;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightTime;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngleTime;
import org.usfirst.frc.team6560.robot.commands.grabber.RotateGrabberTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightTimeTime extends CommandGroup {

	public RightTimeTime(String givenData, double driveScalar, double rotateScalar) {
		
		char switchPos = 0;
		char scalePos = 0;
		try {
			switchPos = givenData.charAt(0);
			scalePos = givenData.charAt(1);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (switchPos == 'R') {
			System.out.println("Going to left switch from left station");
			addParallel(new RotateArmTime(0.8, 0.7));
			addParallel(new RotateGrabberTime(0.4, 0.7));
			addSequential(new DriveStraightTime(2.5*driveScalar, 0.7));
			addSequential(new WaitCommand(0.5));
			addSequential(new TurnToAngleTime(0.80*rotateScalar, -0.85));
			addSequential(new WaitCommand(0.5));
			addSequential(new DriveStraightTime(0.8*driveScalar, 0.7));
			addSequential(new OpenIntakeArms());
			addSequential(new DriveStraightTime(0.5*driveScalar, -0.7));
			addSequential(new TurnToAngleTime(0.80*rotateScalar, 0.85));
		} else if (scalePos == 'R'){
			System.out.println("I guess the game just hates us...");
		} else {
			System.out.println("I guess the game just hates us...");
		}
	}
}
