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
public class RightSwitch extends CommandGroup {

	String gameData;
    public RightSwitch(String givenData) {
    	gameData = givenData;
    	char switchPos = gameData.charAt(0);
    	if(switchPos == 'L') {
    		
    	}
    	else {
    		addParallel(new PIDSetSwitch());
    		addSequential(new DriveStraightToDistance(120, 0.8));
    		addSequential(new TurnToAngle(90, 0.7));
    		addSequential(new DriveStraightToDistance(16, 0.8));
    		addSequential(new OpenIntakeArms(0.5));
    		addSequential(new DriveStraightToDistance(-16, 0.8));
    		addParallel(new PIDSetIntake());
    	}
    }
}
