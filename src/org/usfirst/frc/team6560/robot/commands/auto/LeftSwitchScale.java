package org.usfirst.frc.team6560.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchScale extends CommandGroup {

	String gameData;
	
    public LeftSwitchScale(String givenData) {
    	gameData = givenData;
    	char switchPos = 0;
    	char scalePos = 0;
    	try {
    		switchPos = gameData.charAt(0);
    		scalePos = gameData.charAt(1);
    	}
    	catch(NullPointerException npe) {
    		System.out.println("Game data Nonexistent!");
    	}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
