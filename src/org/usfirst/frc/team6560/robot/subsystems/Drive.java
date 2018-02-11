package org.usfirst.frc.team6560.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team6560.util.ADIS16448_IMU;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.TankDriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
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
	
	//Sensors
	public AnalogInput ultra;
	public ADXRS450_Gyro gyro;
	public ADIS16448_IMU imu;
	
	public Drive() {
		globalDriveSpeed = 0.7;
		
		
		frontLeftDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTLEFT);
		rearLeftDrive = new WPI_TalonSRX(CAN.DRIVE_REARLEFT);
		frontRightDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTRIGHT);
		rearRightDrive = new WPI_TalonSRX(CAN.DRIVE_REARRIGHT);
		left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
		right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);
		
		frontLeftDrive.setInverted(true);
		rearLeftDrive.setInverted(true);
		frontRightDrive.setInverted(true);
		rearRightDrive.setInverted(true);


		drivetrain = new DifferentialDrive(left, right);
		
		ultra = new AnalogInput(0);
		gyro = new ADXRS450_Gyro();
		imu = new ADIS16448_IMU();
		
		gyro.calibrate();
		gyro.reset();
		imu.calibrate();
		imu.reset();
	}
	
	public void tankDriveWithJoysticks(double left, double right) {
		drivetrain.tankDrive(left*globalDriveSpeed, right*globalDriveSpeed);
	}
	
	public void stopDrive() {
		frontLeftDrive.set(0);
		rearLeftDrive.set(0);
		frontRightDrive.set(0);
		rearRightDrive.set(0);
	}
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public void increaseDriveSpeed() {
		if (globalDriveSpeed+0.1<=1.0) {
			globalDriveSpeed+=0.1;
		}
	}
	
	public void decreaseDriveSpeed() {
		if (globalDriveSpeed-0.1>=0.1) {
			globalDriveSpeed-=0.1;
		}
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveWithJoysticks());
    }
}
