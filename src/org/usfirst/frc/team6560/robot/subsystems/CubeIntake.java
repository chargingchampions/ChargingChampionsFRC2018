package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.IntakeCube;
import org.usfirst.frc.team6560.robot.commands.IntakeCubeWithJoystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {

	WPI_TalonSRX intakeMotor1 = new WPI_TalonSRX(CAN.GRABBER_LEFT);
	WPI_TalonSRX intakeMotor2 = new WPI_TalonSRX(CAN.GRABBER_RIGHT);
	WPI_TalonSRX rotationMotor = new WPI_TalonSRX(CAN.GRABBER_ROTATION);
	
    public CubeIntake() {
    	intakeMotor1.setSafetyEnabled(false);
    	intakeMotor2.setSafetyEnabled(false);
    	intakeMotor1.setInverted(false);
    	intakeMotor2.setInverted(true);
    	
    	rotationMotor.configForwardSoftLimitThreshold(292488, 0);
    	rotationMotor.configReverseSoftLimitThreshold(142521, 0);
    	rotationMotor.configForwardSoftLimitEnable(false, 0);
    	rotationMotor.configReverseSoftLimitEnable(false, 0);
    }

    public void intakeCube(double speed) {
    	intakeMotor1.set(speed);
    	intakeMotor2.set(speed);
    }
    
    public void shootCube(double speed) {
    	intakeMotor1.set(-1 * speed);
    	intakeMotor2.set(-1 * speed);
    }
    
    public void stopIntake() {
    	intakeMotor1.set(0);
    	intakeMotor2.set(0);
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
    
    public void setDefaultPosition() {
    	rotateGrabber(0.7);
    }
    
    public void setHorizontalPosition() {
    	rotateGrabber(-0.7);
    }
    
    public void disableSoftLimits() {
    	rotationMotor.configForwardSoftLimitEnable(false, 0);
    	rotationMotor.configReverseSoftLimitEnable(false, 0);
    }
    
    public void enableSoftLimits() {
    	rotationMotor.configForwardSoftLimitEnable(true, 0);
    	rotationMotor.configReverseSoftLimitEnable(true, 0);
    }
    
    
    
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCubeWithJoystick());
    }
}

