package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.grabber.RotateGrabberSecondPOV;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Grabber extends PIDSubsystem {

	WPI_TalonSRX rotationMotor = new WPI_TalonSRX(CAN.GRABBER_ROTATION);

	
	public Grabber() {
		super("Grabber", Robot.grabberPVal, Robot.grabberIVal, Robot.grabberDVal);
		rotationMotor.setInverted(false);
		//rotationMotor.setInverted(true);
		rotationMotor.setSafetyEnabled(false);
		rotationMotor.getSensorCollection().setQuadraturePosition(0, 100);
		setAbsoluteTolerance(Robot.grabberAbsTol);
		getPIDController().setContinuous(false);
		getPIDController().disable();
		getPIDController().setSetpoint(0);
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

	/**
	 * sets the PID constants and absolute tolerance to the current instance variables held by Robot.java
	 * resets PID position to 0, sets the setpoint to 0, and disables the PID subsystem
	 */
	public void refreshSubsystem() {

		getPIDController().setPID(Robot.grabberPVal, Robot.grabberIVal, Robot.grabberDVal);
		rotationMotor.getSensorCollection().setQuadraturePosition(0, 100);
		setAbsoluteTolerance(Robot.grabberAbsTol);
		getPIDController().disable();
		getPIDController().setSetpoint(0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RotateGrabberSecondPOV());
	}

	@Override
	protected double returnPIDInput() {
		return rotationMotor.getSensorCollection().getQuadraturePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		rotationMotor.pidWrite(output);

	}
}
