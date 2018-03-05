package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveStraightBackwards extends Command {

	public TankDriveStraightBackwards() {
		requires(Robot.drive);
	}

	protected void initialize() {
		Robot.drive.gyro.reset();
	}

	protected void execute() {
		Robot.drive.driveStraightWithGyro(-1 * Drive.globalDriveSpeed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stopDrive();
	}

	protected void interrupted() {
		end();
	}
}
