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
public class LeftSwitch extends CommandGroup {

	String gameData;
	
    public LeftSwitch(String givenData) {
    	gameData = givenData;
    	char switchPos = 'L';
    	if (gameData.length()>0)
    	switchPos = gameData.charAt(0);
    	if(switchPos == 'L') {
    		/*addParallel(new PIDSetSwitch()); //TODO: fix safety value
        	addSequential(new DriveStraightToDistance(128, 0.8));
        	addSequential(new TurnToAngle(90, 1.0));
        	addSequential(new DriveStraightToDistance(6, 0.8));
        	addSequential(new ShootCube());
        	addParallel(new PIDSetIntake());
        	addSequential(new DriveStraightToDistance(-16, 0.8));
        	*/
    		System.out.println("Going to left switch...");
        	addParallel(new PIDSetSwitch());
        	addSequential(new DriveStraightToDistance(1400, 0.8));
        	addSequential(new TurnToAngle(-85, 0.4));
        	//addSequential(new DriveStraightToDistance(5, 0.25));
        	addSequential(new OpenIntakeArms(0.5));
        	//addParallel(new PIDSetIntake());
        	addSequential(new DriveStraightToDistance(-10, 0.8));
    	}
    	else {
    		System.out.println("Going to right switch...");
    		//left station, right switch
    		addParallel(new PIDSetSwitch());
    		addSequential(new DriveStraightToDistance(60, 0.8));
    		addSequential(new TurnToAngle(-90, 0.7));
    		addSequential(new DriveStraightToDistance(200, 0.8));
    		addSequential(new TurnToAngle(90, 0.7));
    		addSequential(new DriveStraightToDistance(60, 0.8));
    		addSequential(new OpenIntakeArms(0.5));
    		addParallel(new PIDSetIntake());
    		addSequential(new DriveStraightToDistance(-16, 0.8));
    	}
    }
}
