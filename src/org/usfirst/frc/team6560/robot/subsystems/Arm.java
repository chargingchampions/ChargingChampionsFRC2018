package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	WPI_TalonSRX jointMotor = new WPI_TalonSRX(CAN.ARM);
    
    public Arm() {
    	jointMotor.setSafetyEnabled(false);
    	
    }

    public void rotate(double speed) {
    	jointMotor.set(speed);
    }
    
    public void stopRotate() {
    	jointMotor.set(0);
    }
    public void initDefaultCommand() {
    	setDefaultCommand(new RotateArmWithJoystick());
    }
}

