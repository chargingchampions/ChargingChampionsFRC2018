package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurnToAngle extends Command {

    double angleToTurnTo, speed, tolerance;
    PIDOutput driveRotateOutput;
    PIDController driveRotatePIDControl;

    public PIDTurnToAngle(double angleToTurnTo, double speed, double tolerance) {
        requires(Robot.drive);
        this.angleToTurnTo = angleToTurnTo;
        this.speed = speed;
        this.tolerance = tolerance;
        driveRotateOutput = new DriveRotatePIDOutput();
        driveRotatePIDControl = new PIDController(Robot.driveRotatePVal, Robot.driveRotateIVal, Robot.driveRotateDVal, Robot.drive.gyro, driveRotateOutput);
        //driveRotatePIDControl.setContinuous();
        //TODO: PID controllers should not have to be tuned after finding correct values, so perhaps hardcode them into the code later on
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drive.gyro.reset();
        driveRotatePIDControl.setSetpoint(angleToTurnTo);

        // This is the point at which the error is small enough to be tolerated
        driveRotatePIDControl.setAbsoluteTolerance(tolerance);

        // The maximum value given to the motor controller
        driveRotatePIDControl.setOutputRange(-speed, speed);

        driveRotatePIDControl.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(driveRotatePIDControl.getSetpoint() - Robot.drive.gyro.getAngle()) < tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
      driveRotatePIDControl.disable();
      Robot.drive.stopDrive();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
