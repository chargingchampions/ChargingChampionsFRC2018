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
    	super("Grabber", Robot.grabberPVal, Robot.grabberIVal, Robot.grabberDVal);
    	rotationMotor.setSafetyEnabled(false);
    	rotationMotor.getSensorCollection().setQuadraturePosition(0,  1);
    	setAbsoluteTolerance(Robot.grabberAbsTol);
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
    
    public void resetQuadraturePosition() {
    	rotationMotor.getSensorCollection().setQuadraturePosition(0,  1);
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
		rotationMotor.pidWrite(output*0.6);
		
	}
}

