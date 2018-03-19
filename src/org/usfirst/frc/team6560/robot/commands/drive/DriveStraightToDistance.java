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
	Timer shutdownTimer;

    public DriveStraightToDistance(double distanceIn, double speedIn) {
        requires(Robot.drive);
        shutdownTimer = new Timer();
        distance = Math.abs(distanceIn);
        if (distanceIn < 0)
        speedToDrive = -Math.abs(speedIn);
        else 
        speedToDrive = Math.abs(speedIn);
    }

    protected void initialize() {
    	Robot.drive.gyro.reset();
    	//Robot.drive.imu.reset();
    	Robot.drive.drive_enc_left.reset();
    	Robot.drive.drive_enc_right.reset();
    	shutdownTimer.reset();
    	shutdownTimer.start();
    	Robot.drive.driveStraightWithGyro(speedToDrive);
    	Timer.delay(0.2);
    }

    protected void execute() {
    		Robot.drive.driveStraightWithGyro(speedToDrive);
    }

    protected boolean isFinished() {

    	return Robot.drive.getAbsDistance() == -1 || Robot.drive.getAbsDistance()>= distance
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
