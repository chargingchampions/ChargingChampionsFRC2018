package org.usfirst.frc.team6560.robot.commands;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MecanumDriveWithJoysticks extends Command {

    public MecanumDriveWithJoysticks() {
        requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double x = Robot.oi.getXAxis();
    	double y = Robot.oi.getYAxis();
    	double z = Robot.oi.getZAxis();
    	double gyroAngle = Robot.drive.getGyroAngle();
    	Robot.drive.mecanumDriveWithJoysticks(x, y, z, gyroAngle);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.stopDrive();
    }

    protected void interrupted() {
    	end();
    }
}
