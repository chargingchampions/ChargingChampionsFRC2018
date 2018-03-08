package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToDistance extends Command {

	double distance;
	double speedToDrive;
	double distanceToSlowDown = 6;
	Timer shutdownTimer;

    public DriveStraightToDistance(double distanceIn, double speedIn) {
        requires(Robot.drive);
        shutdownTimer = new Timer();
        distance = Math.abs(distanceIn);
        if (distanceIn < 0) {
        	speedToDrive = -1 * Math.abs(speedIn);
        } else {
        	speedToDrive = Math.abs(speedIn);
        	}     
    }

    protected void initialize() {
    	Robot.drive.imu.reset();
    	Robot.drive.drive_enc_left.reset();
    	Robot.drive.drive_enc_right.reset();
    	shutdownTimer.reset();
    	shutdownTimer.start();
    }

    protected void execute() {
    	if (Math.abs(Robot.drive.drive_enc_right.getDistance() - distance) < distanceToSlowDown) {
    		Robot.drive.driveStraightWithGyro((Math.abs(Robot.drive.drive_enc_right.getDistance() - distance)/distanceToSlowDown)*Robot.drivePVal);
    	} else {
    		Robot.drive.driveStraightWithGyro(speedToDrive);
    	}
    }

    protected boolean isFinished() {
        //return (Math.abs(Robot.drive.drive_enc_left.getDistance() + Robot.drive.drive_enc_right.getDistance())) / 2 >= Math.abs(distanceToDriveAdjusted) || Robot.drive.drive_enc_left.getStopped() || Robot.drive.drive_enc_right.getStopped();
    	//TODO: add the left encoder for this too?

    	return Math.abs(Robot.drive.drive_enc_right.getDistance()) >= distance
    			|| shutdownTimer.get() > distance*0.032/Math.abs(speedToDrive);
    }

    protected void end() {
    	Robot.drive.stopDrive();
    	shutdownTimer.stop();
    	System.out.println("The driveStraightToDistance has ended...");
    }

    protected void interrupted() {
    	end();
    }
}
