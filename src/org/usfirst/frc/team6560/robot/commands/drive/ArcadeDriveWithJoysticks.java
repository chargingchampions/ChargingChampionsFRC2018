package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDriveWithJoysticks extends Command {

	public ArcadeDriveWithJoysticks() {
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.arcadeDrive(-Robot.oi.getLeftYAxis(), Robot.oi.getRightXAxis() * 90);
	}

	// Make this return true when this Command no longer needs to run execute()
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
