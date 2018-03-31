package org.usfirst.frc.team6560.robot.commands.climber;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbReverse extends Command {

	public ClimbReverse() {
		requires(Robot.climber);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.climber.climb(-0.7, 0.0);
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
