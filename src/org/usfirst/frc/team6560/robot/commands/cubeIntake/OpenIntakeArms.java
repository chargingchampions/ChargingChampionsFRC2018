package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenIntakeArms extends Command {
	Timer shutdownTimer = null;
	double time;

	public OpenIntakeArms() {
		requires(Robot.cubeIntake);
	}

	public OpenIntakeArms(double time) {
		requires(Robot.cubeIntake);
		shutdownTimer = new Timer();
		this.time = time;
	}

	protected void initialize() {
		if (shutdownTimer != null) {
			shutdownTimer.start();
		}
		Robot.cubeIntake.openArm();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		if (shutdownTimer != null) {
			return shutdownTimer.get() >= time;
		} else
			return false;
	}

	protected void end() {
		if (shutdownTimer != null) {
			shutdownTimer.stop();
		}
		Robot.cubeIntake.closeArm();
	}

	protected void interrupted() {
		end();
	}
}