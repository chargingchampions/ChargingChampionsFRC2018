package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

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
		Robot.cubeIntake.intakeCube(0.50);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.cubeIntake.closeArm();
	}

	protected void interrupted() {
		end();
	}
}
