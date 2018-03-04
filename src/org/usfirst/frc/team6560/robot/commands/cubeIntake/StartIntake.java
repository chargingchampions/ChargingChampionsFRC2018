package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartIntake extends Command {

	public StartIntake() {
		requires(Robot.cubeIntake);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.cubeIntake.openArm();
		Timer.delay(0.2);
		Robot.cubeIntake.intakeCube(0.4);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		Robot.cubeIntake.closeArm();
		for (int i = 10; i > 0; i--) {
			Timer.delay(0.1);
			Robot.cubeIntake.intakeCube(0.2 + ((i / 10) * 0.2));
		}
	}
}
