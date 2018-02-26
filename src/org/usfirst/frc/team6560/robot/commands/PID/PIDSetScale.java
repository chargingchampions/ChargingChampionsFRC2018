package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetScale extends Command {
	
	boolean safe, arrived;

    public PIDSetScale() {
        requires(Robot.arm);
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("PID Scale starting...");
    	safe = false;
    	arrived = false;
    	Robot.arm.disable();
    	Robot.arm.setSetpoint(Robot.armScaleSetpoint);
    	Robot.grabber.setSetpoint(Robot.grabberSafetySetpoint);
    	Robot.grabber.enable();
    	System.out.println("PID Scale grabber set to safety!");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!safe) {
    		if (Math.abs(Robot.grabber.getPosition() - Robot.grabber.getSetpoint()) < Robot.grabberAbsTol) {
    			safe = true;
    			System.out.println("Scale PID safe!");
    		} else {
    	    	Robot.arm.enable();
    	    	System.out.println("Scale PID arm enabled!");
    		}
    	}
    	
    	if (!arrived) {
    		if (Math.abs(Robot.arm.getPosition() - Robot.arm.getSetpoint()) < Robot.armAbsTol) {
    			arrived = true;
    			System.out.println("Scale PID arm arrived!");
    		}
    	} else {
	    	Robot.grabber.setSetpoint(Robot.grabberScaleSetpoint);
	    	System.out.println("Scale PID grabber set to scale!");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(Robot.oi.getSecondYAxis())>0.25) || (Math.abs(Robot.arm.getSetpoint() - Robot.arm.getPosition()) < Robot.armAbsTol && Math.abs(Robot.grabber.getSetpoint() - Robot.grabber.getPosition()) < Robot.grabberAbsTol);
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Scale PID EXITING!!!!");
    	Robot.arm.disable();
    	Robot.grabber.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
