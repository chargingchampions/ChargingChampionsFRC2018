package org.usfirst.frc.team6560.robot.commands;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCube extends Command {

	double speedToShoot;
	
    public ShootCube(double speed) {
    	requires(Robot.cubeIntake);
    	speedToShoot = speed;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.cubeIntake.shootCube(speedToShoot);
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
