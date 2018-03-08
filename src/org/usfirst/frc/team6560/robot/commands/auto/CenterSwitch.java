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
public class CenterSwitch extends CommandGroup {
	String gameData;

    public CenterSwitch(String givenData) {
    	gameData = givenData;
    	char switchPos = 0;
    	try {
    		switchPos = gameData.charAt(0);
    	}
    	catch(NullPointerException npe) {
    		System.out.println("Game data Nonexistent!");
    	}
    	if(switchPos == 'L') {
    		System.out.println("Going to left switch...");
    		addParallel(new PIDSetSwitch());
        	addSequential(new DriveStraightToDistance(60, 0.8));
        	addSequential(new TurnToAngle(90, 0.7));
        	addSequential(new DriveStraightToDistance(90, 0.8));
        	addSequential(new TurnToAngle(-90, 0.7));
        	addSequential(new DriveStraightToDistance(60, 0.8));
        	addSequential(new OpenIntakeArms(0.5));
        	addParallel(new PIDSetIntake());
        	addSequential(new DriveStraightToDistance(-16, 0.8));
    	}
    	else {
    		System.out.println("Going to right switch...");
    		addParallel(new PIDSetSwitch());
        	addSequential(new DriveStraightToDistance(60, 0.8));
        	addSequential(new TurnToAngle(-90, 0.7));
        	addSequential(new DriveStraightToDistance(90, 0.8));
        	addSequential(new TurnToAngle(90, 0.7));
        	addSequential(new DriveStraightToDistance(60, 0.8));
        	addSequential(new OpenIntakeArms(0.5));
        	addParallel(new PIDSetIntake());
        	addSequential(new DriveStraightToDistance(-16, 0.8));
    	}
    }
}
