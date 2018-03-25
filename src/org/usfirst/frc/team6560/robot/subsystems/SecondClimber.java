package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.secondClimber.RotateSecondClimberStop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**d
 *
 */
public class SecondClimber extends Subsystem {
	WPI_TalonSRX secondClimberMotor = new WPI_TalonSRX(CAN.SECONDCLIMBER);

	public SecondClimber() {
		secondClimberMotor.setSafetyEnabled(false);
	}
	
	public void rotateSecondClimber(double speed) {
		secondClimberMotor.set(speed);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new RotateSecondClimberStop());
	}
}
