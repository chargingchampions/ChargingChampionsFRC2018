package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveRotatePIDOutput implements PIDOutput {
	public void pidWrite(double d) {
		SmartDashboard.putNumber("Motors Rotate PID", d);
		Robot.drive.tankDriveWithJoysticks(d, -1 * d);
	}
}
