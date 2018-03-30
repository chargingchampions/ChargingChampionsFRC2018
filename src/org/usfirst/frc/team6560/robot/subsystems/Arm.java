package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.arm.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {
	WPI_TalonSRX jointMotor1 = new WPI_TalonSRX(CAN.ARM1);
	WPI_TalonSRX jointMotor2 = new WPI_TalonSRX(CAN.ARM2);
	public DigitalInput halleffect;

	/**
	 * creates new arm subsystem, initializes motors and PID subsystem
	 * Important: sets one of the motors to slave mode
	 */
	public Arm() {
		super("Arm", Robot.armPVal, Robot.armIVal, Robot.armDVal);
		halleffect = new DigitalInput(1); // match this value with the DIO port
		jointMotor1.set(ControlMode.Follower, jointMotor2.getDeviceID());
		//jointMotor2.setInverted(true);
		jointMotor1.setSafetyEnabled(false);
		jointMotor2.setSafetyEnabled(false);
		jointMotor2.getSensorCollection().setQuadraturePosition(0, 100);
		setAbsoluteTolerance(Robot.armAbsTol);
		getPIDController().setContinuous(false);
		getPIDController().disable();
		getPIDController().setSetpoint(0);


	}

	/**
	 * rotate method is used to set the speed of the motors responsible for rotating the arm
	 * @param speed for rotation of the arm, positive is up, negative is down
	 */
	public void rotate(double speed) {
		jointMotor2.set(speed);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RotateArmWithJoystick());
	}

	protected double returnPIDInput() {
		return jointMotor2.getSensorCollection().getQuadraturePosition();
	}

	/**
	 * sets the PID constants and absolute tolerance to the current instance variables held by Robot.java
	 * resets PID position to 0, sets the setpoint to 0, and disables the PID subsystem
	 */
	public void refreshSubsystem() {
		getPIDController().setPID(Robot.armPVal, Robot.armIVal, Robot.armDVal);
		jointMotor2.getSensorCollection().setQuadraturePosition(0, 100);
		setAbsoluteTolerance(Robot.armAbsTol);
		getPIDController().disable();
		getPIDController().setSetpoint(0);

	}

	protected void usePIDOutput(double output) {
		jointMotor2.pidWrite(output);

	}
}