package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	double angle; 
	double speed;
	double angleToSlowDown = 5;
	Timer shutdownTimer;
	
    public TurnToAngle(double angleToTurn, double speedToTurn) {
        requires(Robot.drive);
        angle = angleToTurn;
        speed = speedToTurn;
        shutdownTimer = new Timer();
    }

    protected void initialize() {
    	Robot.drive.gyro.reset();
    	shutdownTimer.reset();
    	shutdownTimer.start();
    }

    protected void execute() {
    	if(angle > 0) {
    		if (Math.abs(angle-Robot.drive.getGyroAngle()) <= angleToSlowDown) {
    			Robot.drive.spinClockwise((Math.abs(angle-Robot.drive.getGyroAngle())/angleToSlowDown)*Robot.driveRotatePVal);
    		} else {
    			Robot.drive.spinClockwise(speed);
    		}
    	}
    	if(angle < 0) {
    		if (Math.abs(angle-Robot.drive.getGyroAngle()) <= angleToSlowDown) {
    			Robot.drive.spinCounterClockwise((Math.abs(angle-Robot.drive.getGyroAngle())/angleToSlowDown)*Robot.driveRotatePVal);
    		} else {
        		Robot.drive.spinCounterClockwise(speed);
    		}
    	}
    }

    protected boolean isFinished() {
    	//TODO: if the gyro disconnects, you need to be able to stop this command from running forever!!!
        if(angle > 0)
        	return Math.abs(Robot.drive.getGyroAngle() - angle) <= Robot.driveRotateAbsTol || Robot.drive.getGyroAngle() >= angle;
        else if (angle < 0)
        	return Math.abs(Robot.drive.getGyroAngle() - angle) <= Robot.driveRotateAbsTol || Robot.drive.getGyroAngle() <= angle;
        else
        	return shutdownTimer.get() > 5.0; //Automatically stops this command after 5 seconds
    }

    protected void end() {
    	Robot.drive.stopDrive();
    	shutdownTimer.stop();
    }

    protected void interrupted() {
    	end();
    }
}
