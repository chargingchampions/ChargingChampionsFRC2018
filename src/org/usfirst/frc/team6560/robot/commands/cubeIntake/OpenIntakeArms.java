package org.usfirst.frc.team6560.robot.commands.cubeIntake;

import org.usfirst.frc.team6560.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenIntakeArms extends Command {
	Timer shutdownTimer = null;
	double time;

    public OpenIntakeArms() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cubeIntake);
    }
    
    public OpenIntakeArms(double time) {
    	requires(Robot.cubeIntake);
    	shutdownTimer = new Timer();
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(shutdownTimer != null) {
    	shutdownTimer.start();
    	}
    	Robot.cubeIntake.openArm();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(shutdownTimer != null) {
        	return shutdownTimer.get() >= time;
        }
        else
        	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(shutdownTimer != null) {
    		shutdownTimer.stop();
    	}
    	Robot.cubeIntake.closeArm();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}