package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class DriveDistancePIDOutput implements PIDOutput {
	public void pidWrite(double d) {
		Robot.drive.driveStraightWithGyro(d);
	}
}
