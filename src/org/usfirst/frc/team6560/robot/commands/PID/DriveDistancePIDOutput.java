package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistancePIDOutput implements PIDOutput {
	public void pidWrite(double d) {
		SmartDashboard.putNumber("Motors Drive Straight PID", d);
		Robot.drive.driveStraightWithGyro(d);
	}
}
