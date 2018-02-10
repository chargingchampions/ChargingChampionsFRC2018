package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	

	Solenoid solenoid0 = new Solenoid(CAN.SOLENOID_0);
	Compressor compressor_0 = new Compressor(CAN.COMPRESSOR_ID);
	
	public Pneumatics() {
    	compressor_0.setClosedLoopControl(true);
    	compressor_0.start();
	}


    public void openArm() {
    	solenoid0.set(true);
    }
    
    public void closeArm() {
    	solenoid0.set(false);
    }

    public void initDefaultCommand() {
    	compressor_0.start();
    }
}

