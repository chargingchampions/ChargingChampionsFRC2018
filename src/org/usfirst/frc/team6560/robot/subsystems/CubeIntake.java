package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.IntakeCube;
import org.usfirst.frc.team6560.robot.commands.IntakeCubeWithJoystick;
import org.usfirst.frc.team6560.robot.commands.MecanumDriveWithJoysticks;
import org.usfirst.frc.team6560.robot.commands.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {

	WPI_TalonSRX intakeMotor1 = new WPI_TalonSRX(CAN.GRABBER_LEFT);
	WPI_TalonSRX intakeMotor2 = new WPI_TalonSRX(CAN.GRABBER_RIGHT);
    
    public CubeIntake() {
    	intakeMotor1.setSafetyEnabled(false);
    	intakeMotor2.setSafetyEnabled(false);
    	
    }

    public void intakeCube(double speed) {
    	intakeMotor1.set(speed);
    	intakeMotor2.set(speed);
    }
    
    public void shootCube(double speed) {
    	intakeMotor1.set(-1 * speed);
    	intakeMotor2.set(-1 * speed);
    }
    
    public void stopIntake() {
    	intakeMotor1.set(0);
    	intakeMotor2.set(0);
    }
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeCubeWithJoystick());
    }
}

