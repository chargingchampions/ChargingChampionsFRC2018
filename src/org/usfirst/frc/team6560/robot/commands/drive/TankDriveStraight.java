package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveStraight extends Command {

	boolean useConstructor = false;
	double speed = 0;
	public TankDriveStraight() {
		requires(Robot.drive);
	}
	
	public TankDriveStraight(double speedIn) {
		requires(Robot.drive);
		speed = speedIn;
		useConstructor = true;
	}

	protected void initialize() {
		Robot.drive.gyro.reset();
		//Robot.drive.imu.reset();
	}

	protected void execute() {
		if (useConstructor) {
			Robot.drive.driveStraightWithGyro(speed);
		} else {
			Robot.drive.driveStraightWithGyro(Drive.globalDriveSpeed);
		}
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
