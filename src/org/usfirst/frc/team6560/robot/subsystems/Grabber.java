package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.grabber.RotateGrabberSecondPOV;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

	WPI_TalonSRX rotationMotor = new WPI_TalonSRX(CAN.GRABBER_ROTATION);

	
	public Grabber() {
		rotationMotor.setInverted(false);
		//rotationMotor.setInverted(true);
		rotationMotor.setSafetyEnabled(false);
		rotationMotor.getSensorCollection().setQuadraturePosition(0, 100);
		// TODO: add a soft limit encoder value that updates based on arm encoder,
		// probably a trig function
		
	}

	/**
	 * rotates the grabber, positive is rotation outward from safety
	 * @param speed
	 */
	public void rotateGrabber(double speed) {
		rotationMotor.set(speed);
	}
	
	public double getEncoderPosition() {
		return rotationMotor.getSensorCollection().getQuadraturePosition();
	}

	/**
	 * sets the PID constants and absolute tolerance to the current instance variables held by Robot.java
	 * resets PID position to 0, sets the setpoint to 0, and disables the PID subsystem
	 */
	public void refreshSubsystem() {
		rotationMotor.getSensorCollection().setQuadraturePosition(0, 100);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RotateGrabberSecondPOV());
	}
}
