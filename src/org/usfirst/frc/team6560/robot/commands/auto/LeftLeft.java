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
public class LeftLeft extends CommandGroup {

    public LeftLeft() {
    	/*addParallel(new PIDSetSwitch()); //TODO: fix safety value
    	addSequential(new DriveStraightToDistance(128, 0.8));
    	addSequential(new TurnToAngle(90, 1.0));
    	addSequential(new DriveStraightToDistance(6, 0.8));
    	addSequential(new ShootCube());
    	addParallel(new PIDSetIntake());
    	addSequential(new DriveStraightToDistance(-16, 0.8));
    	*/
    	addParallel(new PIDSetSwitch());
    	addSequential(new DriveStraightToDistance(120, 0.8));
    	addSequential(new TurnToAngle(-90, 0.7));
    	addSequential(new DriveStraightToDistance(7, 0.8));
    	addSequential(new OpenIntakeArms(0.5));
    	
    	addParallel(new PIDSetIntake());
    	addSequential(new DriveStraightToDistance(-16, 0.8));
    	
    }
}