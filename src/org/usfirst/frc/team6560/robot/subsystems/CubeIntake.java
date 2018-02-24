package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.IntakeCubeSlowly;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class CubeIntake extends PIDSubsystem {

	WPI_TalonSRX intakeMotor1 = new WPI_TalonSRX(CAN.GRABBER_LEFT);
	WPI_TalonSRX intakeMotor2 = new WPI_TalonSRX(CAN.GRABBER_RIGHT);
	WPI_TalonSRX rotationMotor = new WPI_TalonSRX(CAN.GRABBER_ROTATION);
	
    public CubeIntake() {
    	super("Grabber", -0.007, 0.0, 0.0);
    	setAbsoluteTolerance(1000);
    	getPIDController().setContinuous(false);
    	getPIDController().disable();
    	getPIDController().setSetpoint(3980);
    	intakeMotor1.setSafetyEnabled(false);
    	intakeMotor2.setSafetyEnabled(false);
    	intakeMotor1.setInverted(false);
    	intakeMotor2.setInverted(true);
    	intakeMotor2.set(ControlMode.Follower, intakeMotor1.getDeviceID());
    }

    public void intakeCube(double speed) {
    	intakeMotor1.set(speed);
    }
    
    public void shootCube(double speed) {
    	intakeMotor1.set(-1 * speed);
    }
    
    public void stopIntake() {
    	intakeMotor1.set(0);
    }
    
    public void rotateGrabber(double speed) {
    	rotationMotor.set(speed);
    }
    
    public void stopRotateGrabber() {
    	rotationMotor.set(0);
    }
    
    public int getGrabberRotationAbsolutePosition() {
    	return rotationMotor.getSensorCollection().getPulseWidthPosition();
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCubeSlowly());
    }

	@Override
	protected double returnPIDInput() {
		return rotationMotor.getSensorCollection().getPulseWidthPosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		rotationMotor.pidWrite(output*0.3);
		
	}
}

