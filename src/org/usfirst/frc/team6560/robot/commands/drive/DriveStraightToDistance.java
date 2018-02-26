package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToDistance extends Command {

	double distanceToDriveAdjusted;
	double speedToDrive;

    public DriveStraightToDistance(double distance, double speed) {
        requires(Robot.drive);
        distanceToDriveAdjusted = Math.abs(distance)/(1+ 0.4166667*speed);
        if (distance < 0) {
        	speedToDrive = -1*Math.abs(speed);
        } else {
        	speedToDrive = Math.abs(speed);
        	}     
    }

    protected void initialize() {
    	Robot.drive.drive_enc_left.reset();
    	Robot.drive.drive_enc_right.reset();
    }

    protected void execute() {
    	Robot.drive.driveStraightWithGyro(speedToDrive);
    }

    protected boolean isFinished() {
        return (Math.abs(Robot.drive.drive_enc_left.getDistance() + Robot.drive.drive_enc_right.getDistance())) / 2 >= Math.abs(distanceToDriveAdjusted);
    }

    protected void end() {
    	Robot.drive.stopDrive();
    }

    protected void interrupted() {
    	end();
    }
}