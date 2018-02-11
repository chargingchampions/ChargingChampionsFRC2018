package org.usfirst.frc.team6560.robot.commands;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {

    public IntakeCube() {
    	requires(Robot.cubeIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.cubeIntake.intakeCube(0.4);
    	Robot.cubeIntake.setDefaultPosition();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.cubeIntake.stopIntake();
    }

    protected void interrupted() {
    	end();
    }
}
