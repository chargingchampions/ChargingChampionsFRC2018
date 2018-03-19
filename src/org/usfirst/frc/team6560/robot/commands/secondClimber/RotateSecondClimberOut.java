package org.usfirst.frc.team6560.robot.commands.secondClimber;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateSecondClimberOut extends Command {

    public RotateSecondClimberOut() {
        requires(Robot.secondClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.secondClimber.rotateSecondClimber(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.secondClimber.rotateSecondClimber(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
