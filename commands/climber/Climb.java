package org.usfirst.frc.team6560.robot.commands.climber;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

	public Climb() {
		requires(Robot.climber);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.climber.climb(Robot.oi.getLeftTrigger(), Robot.oi.getRightTrigger());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.climber.stopClimber();
	}

	protected void interrupted() {
		end();
	}
}
