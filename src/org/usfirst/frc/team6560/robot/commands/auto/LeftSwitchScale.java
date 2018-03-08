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
		} catch (NullPointerException npe) {
			System.out.println("Game data Nonexistent!");
		}
		if (switchPos == 'L') {
			if (scalePos == 'L') {

			} else {

			}
		} else {
			if (scalePos == 'L') {

			} else {

			}
		}
	}
}
