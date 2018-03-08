package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurnToAngle extends Command {

	double angle, speed;
	PIDOutput driveRotateOutput;
	PIDController driveRotatePIDControl;

	public PIDTurnToAngle(double angleToTurnTo, double speed) {
		requires(Robot.drive);
		this.angle = angleToTurnTo;
		this.speed = speed;
		driveRotateOutput = new DriveRotatePIDOutput();
		driveRotatePIDControl = new PIDController(Robot.driveRotatePVal, Robot.driveRotateIVal, Robot.driveRotateDVal,
				Robot.drive.imu, driveRotateOutput);
		// TODO: PID controllers should not have to be tuned after finding correct
		// values, so perhaps hardcode them into the code later on
	}

	protected void initialize() {
		Robot.drive.imu.reset();
		driveRotatePIDControl.setSetpoint(angle);

		// This is the point at which the error is small enough to be tolerated
		driveRotatePIDControl.setPercentTolerance(Robot.driveRotateAbsTol);

		// The maximum value given to the motor controller
		driveRotatePIDControl.setOutputRange(-speed, speed);

		driveRotatePIDControl.enable();

	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return Math.abs(driveRotatePIDControl.getSetpoint() - Robot.drive.getGyroAngle()) < Robot.driveRotateAbsTol;
	}

	protected void end() {
		driveRotatePIDControl.disable();
		Robot.drive.stopDrive();

	}

	protected void interrupted() {
		end();
	}
}