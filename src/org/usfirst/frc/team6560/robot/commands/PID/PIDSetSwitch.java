package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetSwitch extends Command {

    public PIDSetSwitch() {
        requires(Robot.arm);
        requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.enable();
    	Robot.grabber.enable();
    	Robot.arm.setSetpoint(Robot.prefs.getDouble("Arm Switch Setpoint", 5000));
    	Timer.delay(3);
    	Robot.grabber.setSetpoint(Robot.prefs.getDouble("Grabber Switch Setpoint", 200));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
    	Robot.arm.disable();
    	Robot.grabber.disable();
    }
}
