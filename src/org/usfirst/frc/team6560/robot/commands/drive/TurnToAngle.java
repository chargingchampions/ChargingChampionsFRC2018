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
	double angleToSlowDown = 30;
	Timer shutdownTimer;
	
    public TurnToAngle(double angleToTurn, double speedToTurn) {
        requires(Robot.drive);
        angle = angleToTurn;
        speed = speedToTurn;
        shutdownTimer = new Timer();
    }

    protected void initialize() {
    	Robot.drive.imu.reset();
    	shutdownTimer.reset();
    	shutdownTimer.start();
    }

    protected void execute() {
    	if(angle > 0) {
    			Robot.drive.spinClockwise(speed);
    	}
    	else if(angle < 0) {
        		Robot.drive.spinCounterClockwise(speed);
    	}
    }

    protected boolean isFinished() {
        if(angle > 0)
        	return Math.abs(Robot.drive.getGyroAngle() - angle) <= Robot.driveRotateAbsTol || Robot.drive.getGyroAngle() >= angle;
        else if (angle < 0)
        	return Math.abs(Robot.drive.getGyroAngle() - angle) <= Robot.driveRotateAbsTol || Robot.drive.getGyroAngle() <= angle;
        else
        	return shutdownTimer.get() > 4.0; //Automatically stops this command after 4 seconds
    }

    protected void end() {
    	Robot.drive.stopDrive();
    	shutdownTimer.stop();
    }

    protected void interrupted() {
    	end();
    }
}
