package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	WPI_TalonSRX jointMotor1 = new WPI_TalonSRX(CAN.ARM1);
	WPI_TalonSRX jointMotor2 = new WPI_TalonSRX(CAN.ARM2);
	
    
    public Arm() {
    	jointMotor1.setSafetyEnabled(false);
    	jointMotor2.setSafetyEnabled(false);
    	
    }

    public void rotate(double speed) {
    	jointMotor1.set(speed);
    	jointMotor2.set(speed);
    }
    
    public void stopRotate() {
    	jointMotor1.set(0);
    	jointMotor2.set(0);
    }
    
    public int getArmRotationAbsolutePosition() {
    	return jointMotor2.getSensorCollection().getPulseWidthPosition();
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new RotateArmWithJoystick());
    }
    
    public void setDefaultPosition() {
    	//jointMotor2.getSensorCollection().setPulseWidthPosition(newPosition, timeoutMs);
    }
}

