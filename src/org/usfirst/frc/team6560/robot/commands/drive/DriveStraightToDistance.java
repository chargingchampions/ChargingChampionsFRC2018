package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightToDistance extends Command {

	double distanceToDrive;
	double speedToDrive;

    public DriveStraightToDistance(double distance, double speed) {
        requires(Robot.drive);
        distanceToDrive = distance;
        speedToDrive = speed;
    }

    protected void initialize() {
    	Robot.drive.drive_enc_left.reset();
    	Robot.drive.drive_enc_right.reset();
    }

    protected void execute() {
    	Robot.drive.driveStraightWithGyro(speedToDrive);
    }

    protected boolean isFinished() {
        return (Robot.drive.drive_enc_left.getDistance() + Robot.drive.drive_enc_right.getDistance()) / 2 >= distanceToDrive;
    }

    protected void end() {
    	Robot.drive.stopDrive();
    }

    protected void interrupted() {
    	end();
    }
}
