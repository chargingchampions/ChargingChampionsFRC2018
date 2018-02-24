package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.IntakeCubeSlowly;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeIntake extends Subsystem {
	
	Solenoid solenoid0 = new Solenoid(CAN.SOLENOID_0);
	Compressor compressor_0 = new Compressor(CAN.COMPRESSOR_ID);
	WPI_TalonSRX intakeMotor1 = new WPI_TalonSRX(CAN.GRABBER_LEFT);
	WPI_TalonSRX intakeMotor2 = new WPI_TalonSRX(CAN.GRABBER_RIGHT);
	
	public CubeIntake() {
    	compressor_0.setClosedLoopControl(true);
    	compressor_0.start();
    	
    	intakeMotor1.setSafetyEnabled(false);
    	intakeMotor2.setSafetyEnabled(false);
    	intakeMotor1.setInverted(false);
    	intakeMotor2.setInverted(true);
    	intakeMotor2.set(ControlMode.Follower, intakeMotor1.getDeviceID());
	}

	public void intakeCube(double speed) {
    	intakeMotor1.set(speed);
    }
	

    public void shootCube(double speed) {
    	intakeMotor1.set(-1 * speed);
    }
    
    public void stopIntake() {
    	intakeMotor1.set(0);
    }

    public void openArm() {
    	solenoid0.set(true);
    }
    
    public void closeArm() {
    	solenoid0.set(false);
    }

    public void initDefaultCommand() {
    	compressor_0.start();
    	setDefaultCommand(new IntakeCubeSlowly());
    }
}

