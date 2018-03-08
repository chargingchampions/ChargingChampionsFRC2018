package org.usfirst.frc.team6560.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScale extends CommandGroup {

	String gameData;

	public LeftScale(String givenData) {
		gameData = givenData;
		char scalePos = 0;
		try {
			scalePos = gameData.charAt(1);
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (scalePos == 'L') {

		} else {

		}
	}
}
