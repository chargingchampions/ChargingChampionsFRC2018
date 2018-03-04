package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.IntakeCubeSlowly;
import org.usfirst.frc.team6560.robot.commands.grabber.RotateGrabberSecondPOV;
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
		rotationMotor.getSensorCollection().setQuadraturePosition(0, 100);
		setAbsoluteTolerance(Robot.grabberAbsTol);
		getPIDController().setContinuous(false);
		getPIDController().disable();
		getPIDController().setSetpoint(0);
		// TODO: add a soft limit encoder value that updates based on arm encoder,
		// probably a trig function
		rotationMotor.configForwardSoftLimitThreshold(Robot.grabberLowerSoftLimit, 100);
		rotationMotor.configForwardSoftLimitEnable(false, 100);
	}

	public void rotateGrabber(double speed) {
		rotationMotor.set(speed);
	}

	public void stopRotateGrabber() {
		rotationMotor.set(0);
	}

	public void refreshSubsystem() {

		getPIDController().setPID(Robot.grabberPVal, Robot.grabberIVal, Robot.grabberDVal);
		rotationMotor.setSafetyEnabled(false);
		rotationMotor.getSensorCollection().setQuadraturePosition(0, 100);
		setAbsoluteTolerance(Robot.grabberAbsTol);
		getPIDController().setContinuous(false);
		getPIDController().disable();
		getPIDController().setSetpoint(0);
		// TODO: add a soft limit encoder value that updates based on arm encoder,
		// probably a trig function
		rotationMotor.configForwardSoftLimitThreshold(Robot.grabberLowerSoftLimit, 100);
		rotationMotor.configForwardSoftLimitEnable(false, 100);
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
