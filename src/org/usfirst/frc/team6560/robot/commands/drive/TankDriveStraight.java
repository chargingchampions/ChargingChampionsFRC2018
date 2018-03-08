package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveStraight extends Command {

	public TankDriveStraight() {
		requires(Robot.drive);
	}

	protected void initialize() {
		Robot.drive.imu.reset();
	}

	protected void execute() {
		Robot.drive.driveStraightWithGyro(Drive.globalDriveSpeed);
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
