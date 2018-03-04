package org.usfirst.frc.team6560.robot.commands.drive;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveWithJoysticks extends Command {

    public TankDriveWithJoysticks() {
        requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drive.tankDriveWithJoysticks(Robot.oi.getLeftYAxis(), Robot.oi.getRightYAxis());
    }

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
