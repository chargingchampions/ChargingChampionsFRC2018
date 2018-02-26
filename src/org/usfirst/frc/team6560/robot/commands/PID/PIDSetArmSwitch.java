package org.usfirst.frc.team6560.robot.commands.PID;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDSetArmSwitch extends Command {

    public PIDSetArmSwitch() {
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.setSetpoint(Robot.armSwitchSetpoint);
    	Robot.arm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.arm.getPosition() - Robot.arm.getSetpoint()) < Robot.armAbsTol;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
