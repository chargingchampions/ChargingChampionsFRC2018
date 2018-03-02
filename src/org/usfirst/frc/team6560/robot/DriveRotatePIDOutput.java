package org.usfirst.frc.team6560.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveRotatePIDOutput implements PIDOutput {
        public void pidWrite(double d) {
        SmartDashboard.putNumber("Motors", d);
            Robot.drive.arcadeDrive(0, d * -1);
        }
    }
