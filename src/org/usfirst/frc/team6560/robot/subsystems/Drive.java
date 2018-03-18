package org.usfirst.frc.team6560.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.drive.ArcadeDriveWithJoysticks;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Drive extends Subsystem {

	public static double globalDriveSpeed;

	WPI_TalonSRX frontLeftDrive;
	WPI_TalonSRX rearLeftDrive;
	WPI_TalonSRX frontRightDrive;
	WPI_TalonSRX rearRightDrive;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	DifferentialDrive drivetrain;

	// Sensors
	// public AnalogInput ultra;
	public ADXRS450_Gyro gyro;
	// public ADIS16448_IMU imu;

	public Encoder drive_enc_left;
	public Encoder drive_enc_right;

	public Drive() {
		globalDriveSpeed = 0.8;

		frontLeftDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTLEFT);
		rearLeftDrive = new WPI_TalonSRX(CAN.DRIVE_REARLEFT);
		frontRightDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTRIGHT);
		rearRightDrive = new WPI_TalonSRX(CAN.DRIVE_REARRIGHT);
		left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
		right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);

		drivetrain = new DifferentialDrive(left, right);

		frontLeftDrive.setInverted(false);
		rearLeftDrive.setInverted(false);
		frontRightDrive.setInverted(false);
		rearRightDrive.setInverted(true);

		frontLeftDrive.setSafetyEnabled(false);
		rearLeftDrive.setSafetyEnabled(false);
		frontRightDrive.setSafetyEnabled(false);
		rearRightDrive.setSafetyEnabled(false);
		drivetrain.setSafetyEnabled(false);

		// ultra = new AnalogInput(0);
		gyro = new ADXRS450_Gyro();
		// imu = new ADIS16448_IMU();
		gyro.calibrate();
		gyro.reset();
		// imu.calibrate();
		// imu.reset();

		drive_enc_left = new Encoder(8, 9, true, Encoder.EncodingType.k2X); // TODO: Determine DIO ports
		drive_enc_right = new Encoder(6, 7, true, Encoder.EncodingType.k2X);
		initializeEncoders();
		// Should have identical initializations
		// k2x vs. k4x vs. k1x
	}

	/**
	 * sets the speeds of the drive train motors, positive is forward
	 * 
	 * @param left
	 * @param right
	 */
	public void tankDriveWithJoysticks(double left, double right) {
		drivetrain.tankDrive(left * globalDriveSpeed, right * globalDriveSpeed);
	}
	
	public void arcadeDriveWithJoysticks(double left, double right) {
		drivetrain.arcadeDrive(left * globalDriveSpeed, right * globalDriveSpeed);
	}

	public void stopDrive() {
		frontLeftDrive.set(0);
		rearLeftDrive.set(0);
		frontRightDrive.set(0);
		rearRightDrive.set(0);
	}

	public void initializeEncoders() {
		// TODO: ??????????????????????????? learn this stuff
		// drive_enc_left.setMinRate(0);
		drive_enc_left.setDistancePerPulse((3 * Math.PI) / 128);
		// 1 revolution / 256 pulses * 6pi inches diameter / 1 revolution
		drive_enc_left.setReverseDirection(true);
		// drive_enc_left.setSamplesToAverage(0);
		// drive_enc_right.setMinRate(0);
		drive_enc_right.setDistancePerPulse((3 * Math.PI) / 128);
		drive_enc_right.setReverseDirection(false);
		// drive_enc_right.setSamplesToAverage(0);

		drive_enc_right.setMaxPeriod(1);
		drive_enc_left.setMaxPeriod(1);
	}

	/**
	 * 
	 * @return -1 if both encoders are stopped, the average distance of two working
	 *         encoders, or just the distance of the one working encoder
	 */
	public double getAbsDistance() {
		double distance = 0;
		double numWorkingEnc = 0;
		if (!drive_enc_right.getStopped()) {
			distance += Math.abs(Robot.drive.drive_enc_right.getDistance());
			numWorkingEnc++;
		}
		if (!drive_enc_left.getStopped()) {
			distance += Math.abs(Robot.drive.drive_enc_left.getDistance());
			numWorkingEnc++;
		}
		if (numWorkingEnc == 0) {
			return distance / numWorkingEnc;
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @return -9999 if both are stopped, or the distance of one working encoder, or
	 *         the average displacement of the two encoders, will return a positive
	 *         displacement average if the two encoders are conflicting in direction
	 */
	public double getDisplacement() {
		double distanceLeft = 0;
		double distanceRight = 0;
		if (drive_enc_right.getStopped() && drive_enc_left.getStopped()) {
			return -9999;
		} else {
			if (!drive_enc_right.getStopped()) {
				distanceRight += Math.abs(Robot.drive.drive_enc_right.getDistance());
			}
			if (!drive_enc_left.getStopped()) {
				distanceLeft += Math.abs(Robot.drive.drive_enc_left.getDistance());
			}
			if ((distanceLeft > 0 && distanceRight > 0) || (distanceLeft < 0 && distanceRight < 0)) {
				return (distanceLeft + distanceRight) / 2;
			} else if (distanceLeft == 0 && distanceRight != 0) {
				return distanceRight;
			} else if (distanceRight == 0 && distanceLeft != 0) {
				return distanceLeft;
			} else {
				return (Math.abs(distanceLeft) + Math.abs(distanceRight)) / 2;
			}
		}
	}

	/**
	 * drives forward at the given speed with use of gyro, positive is forward
	 * 
	 * @param speed
	 */
	public void driveStraightWithGyro(double speed) {
		// double angle = gyro.getAngle();
		double angle = getGyroAngle();
		drivetrain.arcadeDrive(speed, -0.3 * angle);
	}

	public double getGyroAngle() {
		return -gyro.getAngle();
		// return imu.getAngleX();
	}

	/**
	 * positive is forward
	 * 
	 * @param speed
	 * @param angle
	 */
	public void arcadeDrive(double speed, double angle) {
		drivetrain.arcadeDrive(speed, angle);
	}

	public void increaseDriveSpeed() {
		if (globalDriveSpeed + 0.1 <= 1.0) {
			globalDriveSpeed += 0.1;
		}
	}

	public void decreaseDriveSpeed() {
		if (globalDriveSpeed - 0.1 >= 0.1) {
			globalDriveSpeed -= 0.1;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveWithJoysticks());
	}

}
