package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetIntake extends Command {
	
	boolean safe, arrived;

    public PIDSetIntake() {
        requires(Robot.arm);
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	safe = false;
    	arrived = false;
    	Robot.arm.disable();
		Robot.arm.setSetpoint(Robot.prefs.getDouble("Arm Intake Setpoint", 0));
    	Robot.grabber.setSetpoint(Robot.prefs.getDouble("Grabber Safety Setpoint", 4000));
    	Robot.grabber.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!safe) {
    		if (Math.abs(Robot.grabber.getPosition() - Robot.grabber.getSetpoint()) < Robot.prefs.getDouble("Grabber Absolute Tolerance", 1000)) {
    			safe = true;
    		} else {
    	    	Robot.arm.enable();
    		}
    	}
    	
    	if (!arrived) {
    		if (Math.abs(Robot.arm.getPosition() - Robot.arm.getSetpoint()) < Robot.prefs.getDouble("Arm Absolute Tolerance", 5000)) {
    			arrived = true;
    		}
    	} else {
	    	Robot.grabber.setSetpoint(Robot.prefs.getDouble("Grabber Intake Setpoint", 3000));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Math.abs(Robot.oi.getSecondYAxis())>0.25) || (Math.abs(Robot.arm.getSetpoint() - Robot.arm.getPosition()) < Robot.prefs.getDouble("Arm Disengage Threshold", 5000) && Math.abs(Robot.grabber.getSetpoint() - Robot.grabber.getPosition()) < Robot.prefs.getDouble("Grabber PID Disengage Threshold", 1000));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.disable();
    	Robot.grabber.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
