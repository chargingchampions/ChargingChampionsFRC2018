package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCube extends Command {

	public ShootCube() {
		requires(Robot.cubeIntake);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.cubeIntake.shootCube(0.6);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.cubeIntake.shootCube(0);
	}

	protected void interrupted() {
		end();
	}
}
