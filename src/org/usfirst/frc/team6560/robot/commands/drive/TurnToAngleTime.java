package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngleTime extends Command {

	double time;
	double speed;
	Timer shutdownTimer;

	/**
	 * positive speed is clockwise rotation
	 * @param time
	 * @param speed
	 */
	public TurnToAngleTime(double time, double speed) {
		requires(Robot.drive);
		this.time = time;
		this.speed = speed;
		shutdownTimer = new Timer();
	}

	protected void initialize() {
		shutdownTimer.reset();
		shutdownTimer.start();

	}

	protected void execute() {
		Robot.drive.arcadeDrive(0, speed);
	}

	protected boolean isFinished() {
		return shutdownTimer.get() >= time;
	}

	protected void end() {
		Robot.drive.stopDrive();
		shutdownTimer.stop();
	}

	protected void interrupted() {
		end();
	}
}
