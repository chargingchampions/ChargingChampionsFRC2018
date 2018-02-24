package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.IntakeCubeSlowly;
import org.usfirst.frc.team6560.robot.commands.grabber.RotateGrabberStop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Grabber extends PIDSubsystem {

	
	WPI_TalonSRX rotationMotor = new WPI_TalonSRX(CAN.GRABBER_ROTATION);
	
    public Grabber() {
    	super("Grabber", Robot.prefs.getDouble("Grabber P Value", -0.007), Robot.prefs.getDouble("Grabber I Value", 0.0), Robot.prefs.getDouble("Grabber D Value", 0.0));
    	rotationMotor.setSafetyEnabled(false);
    	rotationMotor.getSensorCollection().setQuadraturePosition(0,  1);
    	setAbsoluteTolerance(Robot.prefs.getDouble("Grabber Absolute Tolerance", 1000));
    	getPIDController().setContinuous(false);
    	getPIDController().disable();
    	getPIDController().setSetpoint(0);
    	
    }

    
    
    
    public void rotateGrabber(double speed) {
    	rotationMotor.set(speed);
    }
    
    public void stopRotateGrabber() {
    	rotationMotor.set(0);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new RotateGrabberStop());
    }

	@Override
	protected double returnPIDInput() {
		return rotationMotor.getSensorCollection().getQuadraturePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		rotationMotor.pidWrite(output*0.3);
		
	}
}

