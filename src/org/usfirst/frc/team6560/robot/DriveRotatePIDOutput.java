class DriveRotatePIDOutput implements PIDOutput {
        public void pidWrite(double d) {
        SmartDashboard.putNumber("Motors", d);
            Robot.drive.arcadeDrive(0, d * -1);
        }
    }
