package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetGrabberScale extends Command {

    public PIDSetGrabberScale() {
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.grabber.setSetpoint(Robot.grabberScaleSetpoint);
    	Robot.grabber.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.grabber.getPosition() - Robot.grabber.getSetpoint()) < Robot.grabberAbsTol;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabber.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
