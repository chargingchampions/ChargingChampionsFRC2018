package org.usfirst.frc.team6560.robot.subsystems;

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
    	super("Grabber", -0.007, 0.0, 0.0);
    	rotationMotor.setSafetyEnabled(false);
    	rotationMotor.getSensorCollection().setQuadraturePosition(0,  1);
    	//setAbsoluteTolerance(1000);
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
    
    public int getGrabberRotationRelativePosition() {
    	return rotationMotor.getSensorCollection().getQuadraturePosition();
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

