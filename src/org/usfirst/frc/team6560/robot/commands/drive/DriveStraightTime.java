package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightTime extends Command {

	double speed, time;
	Timer timer;

	public DriveStraightTime(double timeIn, double speedIn) {
		requires(Robot.drive);
		timer = new Timer();
		time = timeIn;
		speed = speedIn;
	}

	protected void initialize() {
		timer.reset();
		timer.start();
		Robot.drive.gyro.reset();
		//Robot.drive.imu.reset();
	}

	protected void execute() {
		Robot.drive.driveStraightWithGyro(speed);
	}

	protected boolean isFinished() {
		return timer.get() >= time;
	}

	protected void end() {
		Robot.drive.stopDrive();
		timer.stop();
		System.out.println("The driveStraightTime has ended...");
	}

	protected void interrupted() {
		end();
	}
}
