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
        kP = 1;
    }

    protected void initialize() {
    	Robot.drive.gyro.calibrate();
    	Robot.drive.gyro.reset();
    }

    protected void execute() {
    	if(angle > 0) {
    		Robot.drive.spinClockwise(speed * kP * Math.abs(Robot.drive.getGyroAngle() - angle));
    	}
    	Robot.drive.spinCounterClockwise(speed * kP * Math.abs(Robot.drive.getGyroAngle() - angle));
    }

    protected boolean isFinished() {
        return speed * kP * Math.abs(Robot.drive.getGyroAngle() - angle) <= 2;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
