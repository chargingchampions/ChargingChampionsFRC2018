package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.arm.RotateArmTime;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightTime;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngleTime;
import org.usfirst.frc.team6560.robot.commands.grabber.RotateGrabberTime;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.ShootCube;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftTimeTime extends CommandGroup {

	public LeftTimeTime(String givenData, double driveScalar, double rotateScalar) {
		System.out.println(givenData);
		char switchPos = 0;
		char scalePos = 0;
		try {
			switchPos = givenData.charAt(0);
			scalePos = givenData.charAt(1);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (scalePos == 'L') {
			System.out.println("Going to left scale from left station");
			addParallel(new RotateArmTime(0.8, 0.7));
			addParallel(new RotateGrabberTime(0.4, 0.7));
			addSequential(new DriveStraightTime(3.25*driveScalar, 0.8));
			addSequential(new WaitCommand(0.5));
			addSequential(new TurnToAngleTime(0.40*rotateScalar, 0.7));
			addSequential(new WaitCommand(0.5));
			addSequential(new RotateArmTime(1.4, 0.7));
			addSequential(new RotateArmTime(0.6, 0.5));
			addSequential(new RotateGrabberTime(0.65, 0.7));
			addSequential(new DriveStraightTime(0.95*driveScalar, 0.6));
			addSequential(new WaitCommand(0.5));
			addSequential(new ShootCube(1.0, 0.6));
			//addSequential(new RotateGrabberTime(0.30, 0.7));
			addSequential(new DriveStraightTime(1.2*driveScalar, -0.6));
			//addParallel(new RotateGrabberTime(0.8, -0.7));
			//addSequential(new RotateArmTime(1.4, -0.7));
		} else if (scalePos == 'R') {
			System.out.println("Going to right scale from left station");
			addParallel(new RotateArmTime(0.8, 0.7));
			addParallel(new RotateGrabberTime(0.4, 0.7));
			addSequential(new DriveStraightTime(2.8*driveScalar, 0.8)); //1.6 // drive to platform zone on the left
			addSequential(new WaitCommand(0.5));
			addSequential(new TurnToAngleTime(0.60*rotateScalar, 0.7)); // turn into platform zone
			addSequential(new WaitCommand(0.5));
			addSequential(new DriveStraightTime(4.0*driveScalar, 0.8)); // from left side to right side
			addSequential(new WaitCommand(0.5));
			addSequential(new TurnToAngleTime(0.60*rotateScalar, -0.7)); // turn left towards the opposite side
			addSequential(new WaitCommand(0.5));
			addSequential(new DriveStraightTime(0.7*driveScalar, 0.8)); // from platform zone to scale
			addSequential(new WaitCommand(0.5));
			addSequential(new TurnToAngleTime(0.30*rotateScalar, -0.7)); // turn to face scale
			addSequential(new RotateArmTime(0.8, 0.7));
			addSequential(new RotateGrabberTime(0.55, 0.7));
			addSequential(new DriveStraightTime(0.4*driveScalar, 0.6));
			addSequential(new WaitCommand(0.5));
			//addSequential(new OpenIntakeArms());
			addSequential(new ShootCube(1.0, 0.6));
			addSequential(new DriveStraightTime(0.4*driveScalar, -0.6));
		}
	}
}
