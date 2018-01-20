package org.usfirst.frc.team6560.robot.subsystems;

import com.ctre.CANTalon;

import org.usfirst.frc.team6560.util.ADIS16448_IMU;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.MecanumDriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	CANTalon frontLeftDrive;
	CANTalon rearLeftDrive;
	CANTalon frontRightDrive;
	CANTalon rearRightDrive;
	RobotDrive drivetrain;
	
	//Sensors
	public AnalogInput ultra;
	public ADXRS450_Gyro gyro;
	public ADIS16448_IMU imu;
	
	public Drive() {
		frontLeftDrive = new CANTalon(CAN.DRIVE_FRONTLEFT);
		rearLeftDrive = new CANTalon(CAN.DRIVE_REARLEFT);
		frontRightDrive = new CANTalon(CAN.DRIVE_FRONTRIGHT);
		rearRightDrive = new CANTalon(CAN.DRIVE_REARRIGHT);
		drivetrain = new RobotDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
		
		ultra = new AnalogInput(0);
		gyro = new ADXRS450_Gyro();
		imu = new ADIS16448_IMU();
		
		gyro.calibrate();
		gyro.reset();
		imu.calibrate();
		imu.reset();
	}
	
	public void mecanumDriveWithJoysticks(double x, double y, double rotation, double gyroAngle) {
		drivetrain.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
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
        setDefaultCommand(new MecanumDriveWithJoysticks());
    }
}
