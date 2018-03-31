package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.arm.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmBasic extends Subsystem{
	WPI_TalonSRX jointMotor1 = new WPI_TalonSRX(CAN.ARM1);
	WPI_TalonSRX jointMotor2 = new WPI_TalonSRX(CAN.ARM2);

	/**
	 * creates new arm subsystem, initializes motors
	 */
	public ArmBasic() {
		//jointMotor2.setInverted(true);
		jointMotor1.setSafetyEnabled(false);
		jointMotor2.setSafetyEnabled(false);
		jointMotor2.getSensorCollection().setQuadraturePosition(0, 100);


	}

	/**
	 * rotate method is used to set the speed of the motors responsible for rotating the arm
	 * @param speed for rotation of the arm, positive is up, negative is down
	 */
	public void rotate(double speed) {
		jointMotor1.set(speed);
		jointMotor2.set(speed);
	}
	
	public void resetEncoder() {
		jointMotor2.getSensorCollection().setQuadraturePosition(0, 100);
	}
	
	public double getEncoderPosition() {
		return -jointMotor2.getSensorCollection().getQuadraturePosition();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new RotateArmWithJoystick());
	}
}