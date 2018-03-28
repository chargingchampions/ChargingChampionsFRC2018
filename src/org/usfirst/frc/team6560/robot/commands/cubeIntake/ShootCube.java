package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCube extends Command {
	
	Timer shutdownTimer = null;
	double time;
	double speed = 0;

	public ShootCube() {
		requires(Robot.cubeIntake);
	}
	
	public ShootCube(double time) {
		requires(Robot.cubeIntake);
		shutdownTimer = new Timer();
		this.time = time;
	}
	
	public ShootCube(double time, double speed) {
		requires(Robot.cubeIntake);
		shutdownTimer = new Timer();
		this.time = time;
		this.speed = speed;
	}

	protected void initialize() {
		if (shutdownTimer != null) {
			shutdownTimer.start();
		}
		
		if (speed != 0) {
			Robot.cubeIntake.shootCube(speed);
		} else {
			Robot.cubeIntake.shootCube(0.6); 
		}
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		if (shutdownTimer != null) {
			return shutdownTimer.get() >= time;
		} else
			return false;
	}

	protected void end() {
		if (shutdownTimer != null) {
			shutdownTimer.stop();
		}
		Robot.cubeIntake.shootCube(0);
	}

	protected void interrupted() {
		end();
	}
}
