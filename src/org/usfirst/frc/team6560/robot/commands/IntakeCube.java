package org.usfirst.frc.team6560.robot.commands;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {

	double speedToIntake;
	
    public IntakeCube(double speed) {
    	requires(Robot.cubeIntake);
    	speedToIntake = speed;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.cubeIntake.intakeCube(speedToIntake);
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
