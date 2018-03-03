package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDDriveStraightToDistance extends Command {

    double distance, speed;
    PIDOutput driveDistancePIDOutput;
    PIDController driveDistancePIDControl;

    public PIDDriveStraightToDistance(double distance, double speed) {
        requires(Robot.drive);
        this.speed = speed;
        this.distance = distance;
        driveDistancePIDOutput = new DriveDistancePIDOutput();
        driveDistancePIDControl = new PIDController(Robot.drivePVal, Robot.driveIVal, Robot.driveDVal, Robot.drive.drive_enc_right, driveDistancePIDOutput);
        //TODO: PID controllers should not have to be tuned after finding correct values, so perhaps hardcode them into the code later on
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drive.gyro.reset();
        driveDistancePIDControl.setSetpoint(distance);

        // This is the point at which the error is small enough to be tolerated
        driveDistancePIDControl.setPercentTolerance(Robot.driveAbsTol);

        // The maximum value given to the motor controller
        driveDistancePIDControl.setOutputRange(-speed, speed);

        driveDistancePIDControl.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(driveDistancePIDControl.getSetpoint() - Robot.drive.drive_enc_right.getDistance()) < Robot.driveAbsTol;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveDistancePIDControl.disable();
      Robot.drive.stopDrive();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}