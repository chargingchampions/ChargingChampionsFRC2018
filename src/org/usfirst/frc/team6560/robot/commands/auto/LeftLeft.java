package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.PID.PIDSetIntake;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetSwitch;
import org.usfirst.frc.team6560.robot.commands.PID.PIDTurnToAngle;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.ShootCube;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TankDriveStraight;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftLeft extends CommandGroup {

    public LeftLeft() {
    	addParallel(new PIDSetSwitch()); //TODO: fix safety value
    	//addSequential(new PIDTurnToAngle(90, 0.9, 2));
    	//addSequential(new PIDTurnToAngle(-90, 0.9, 2));
    	addSequential(new TurnToAngle(90, 1.0));
    	addSequential(new TurnToAngle(-90, 1.0));
    	//TODO: turn to angle
    	addSequential(new TurnToAngle(90, 1.0));
    	addSequential(new ShootCube());
    	addSequential(new PIDSetIntake());
    }
}