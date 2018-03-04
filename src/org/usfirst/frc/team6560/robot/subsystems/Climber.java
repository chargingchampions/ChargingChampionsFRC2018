package org.usfirst.frc.team6560.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	WPI_TalonSRX climbMotor = new WPI_TalonSRX(0);

	public void initDefaultCommand() {

	}
}
