package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCubeSlowly extends Command {

    public IntakeCubeSlowly() {
        requires(Robot.cubeIntake);
    }

    protected void initialize() {
    	Robot.cubeIntake.closeArm();
    }

    protected void execute() {
    	Robot.cubeIntake.intakeCube((0.1) * (-1 * (Robot.oi.getSecondSlider() - 1)));
    }

    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
