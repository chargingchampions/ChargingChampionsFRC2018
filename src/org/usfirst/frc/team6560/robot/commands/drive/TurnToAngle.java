package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	double angle;
	double speed;
	Timer shutdownTimer;

	public TurnToAngle(double angleToTurn, double speedToTurn) {
		requires(Robot.drive);
		angle = angleToTurn;
		speed = speedToTurn;
		shutdownTimer = new Timer();
	}

	protected void initialize() {
		Robot.drive.imu.reset();
		//Robot.drive.gyro.reset();
		shutdownTimer.reset();
		shutdownTimer.start();

	}

	protected void execute() {
		if (angle > 0) {
			Robot.drive.tankDriveWithJoysticks(speed, -speed);
		} else if (angle < 0) {
			Robot.drive.tankDriveWithJoysticks(-speed, speed);
		}
	}

	protected boolean isFinished() {
		return Math.abs(Robot.drive.getGyroAngle()) >= Math.abs(angle) || shutdownTimer.get() > 3.0;
		// Automatically stops this command after 3 seconds
	}

	protected void end() {
		Robot.drive.stopDrive();
		shutdownTimer.stop();
	}

	protected void interrupted() {
		end();
	}
}
