package org.usfirst.frc.team6560.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team6560.util.ADIS16448_IMU;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.TankDriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	WPI_TalonSRX frontLeftDrive;
	WPI_TalonSRX rearLeftDrive;
	WPI_TalonSRX frontRightDrive;
	WPI_TalonSRX rearRightDrive;
	RobotDrive drivetrain;
	
	//Sensors
	public AnalogInput ultra;
	public ADXRS450_Gyro gyro;
	public ADIS16448_IMU imu;
	
	public Drive() {
		frontLeftDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTLEFT);
		rearLeftDrive = new WPI_TalonSRX(CAN.DRIVE_REARLEFT);
		frontRightDrive = new WPI_TalonSRX(CAN.DRIVE_FRONTRIGHT);
		rearRightDrive = new WPI_TalonSRX(CAN.DRIVE_REARRIGHT);
		drivetrain = new RobotDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
		
		ultra = new AnalogInput(0);
		gyro = new ADXRS450_Gyro();
		imu = new ADIS16448_IMU();
		
		gyro.calibrate();
		gyro.reset();
		imu.calibrate();
		imu.reset();
	}
	
	public void tankDriveWithJoysticks(double left, double right) {
		drivetrain.tankDrive(left, right);
	}
	
	public void driveStraight(double speed) {
		//drivetrain.mecanumDrive_Polar(speed, 0, rotation);
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

    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveWithJoysticks());
    }
}
