package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	double angle; 
	double speed;
	double angleToSlowDown = 5;
	
    public TurnToAngle(double angleToTurn, double speedToTurn) {
        requires(Robot.drive);
        angle = angleToTurn;
        speed = speedToTurn;
    }

    protected void initialize() {
    	Robot.drive.gyro.reset();
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
       	else
        	return Math.abs(Robot.drive.getGyroAngle() - angle) <= Robot.driveRotateAbsTol || Robot.drive.getGyroAngle() <= angle;
    }

    protected void end() {
    	Robot.drive.stopDrive();
    }

    protected void interrupted() {
    	end();
    }
}
