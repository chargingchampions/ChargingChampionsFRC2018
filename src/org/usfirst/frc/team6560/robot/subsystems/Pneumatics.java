package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	public Solenoid solenoid_0 = new Solenoid(CAN.SOLENOID_0);
	public Solenoid solenoid_1 = new Solenoid(CAN.SOLENOID_1);
	public Solenoid solenoid_2 = new Solenoid(CAN.SOLENOID_2);
	public Solenoid solenoid_3 = new Solenoid(CAN.SOLENOID_3);
	public Solenoid solenoid_4 = new Solenoid(CAN.SOLENOID_4);
	public Solenoid solenoid_5 = new Solenoid(CAN.SOLENOID_5);
	public Solenoid solenoid_6 = new Solenoid(CAN.SOLENOID_6);
	public Solenoid solenoid_7 = new Solenoid(CAN.SOLENOID_7);
	//AnalogInput pressureSensor = new AnalogInput(0);
	
	public Compressor compressor_0 = new Compressor(CAN.COMPRESSOR_ID);

	public Pneumatics() {
		compressor_0.setClosedLoopControl(true);
	}
	
	public void openArms() {
		solenoid_0.set(true);
		solenoid_1.set(true);
		solenoid_2.set(true);
		solenoid_3.set(true);
	}
	
	public void closeArms() {
		solenoid_0.set(false);
		solenoid_1.set(false);
		solenoid_2.set(false);
		solenoid_3.set(false);
	}
	
    public void initDefaultCommand() {
    	compressor_0.start();
    	solenoid_0.set(false);
    	solenoid_1.set(false);
    	solenoid_2.set(false);
    	solenoid_3.set(false);
    }
}

