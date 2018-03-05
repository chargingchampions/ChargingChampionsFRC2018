package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.commands.climber.Climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	WPI_TalonSRX climbMotor = new WPI_TalonSRX(7);

	public void climb(double speedUp, double speedDown) {
		if(speedUp > speedDown)
			climbMotor.set(speedUp);
		else
			climbMotor.set(speedDown);
	}
	
	public void stopClimber() {
		climbMotor.stopMotor();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}
}
