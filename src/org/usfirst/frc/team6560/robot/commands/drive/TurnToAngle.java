package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToAngle extends Command {

	double angle; 
	double speed;
	double kP;
	
    public TurnToAngle(double angleToTurn, double speedToTurn) {
        requires(Robot.drive);
        angle = angleToTurn;
        speed = speedToTurn;
    }

    protected void initialize() {
    	Robot.drive.gyro.calibrate();
    	Robot.drive.gyro.reset();
    }

    protected void execute() {
    	if(angle > 0) {
    		Robot.drive.spinClockwise(speed);
    	}
    	Robot.drive.spinCounterClockwise(speed);
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drive.getGyroAngle()) >= Math.abs(angle);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
