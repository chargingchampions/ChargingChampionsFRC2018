package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.Robot;
import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.arm.RotateArmWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {
	WPI_TalonSRX jointMotor1 = new WPI_TalonSRX(CAN.ARM1);
	WPI_TalonSRX jointMotor2 = new WPI_TalonSRX(CAN.ARM2);
    
    public Arm() {
    	super("Arm", Robot.prefs.getDouble("Arm P Value", 0.007), Robot.prefs.getDouble("Arm I Value", 0.0), Robot.prefs.getDouble("Arm D Value", 0.0));
    	jointMotor1.set(ControlMode.Follower, jointMotor2.getDeviceID());
    	jointMotor1.setSafetyEnabled(false);
    	jointMotor2.setSafetyEnabled(false);
    	jointMotor2.setInverted(true);
    	jointMotor2.getSensorCollection().setQuadraturePosition(0, 1);
    	setAbsoluteTolerance(Robot.prefs.getDouble("Arm Absolute Tolerance", 1000));
    	getPIDController().setContinuous(false);
    	getPIDController().disable();
    	getPIDController().setSetpoint(0);
    	
    	
    	
    }

    public void rotate(double speed) {
    	jointMotor2.set(speed);
    }
    
    public void stopRotate() {
    	jointMotor2.set(0);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new RotateArmWithJoystick());
    }
    
    protected double returnPIDInput() {
    	return jointMotor2.getSensorCollection().getQuadraturePosition();
    }
    
   
	protected void usePIDOutput(double output) {
		jointMotor2.pidWrite(output);
		
	}
}