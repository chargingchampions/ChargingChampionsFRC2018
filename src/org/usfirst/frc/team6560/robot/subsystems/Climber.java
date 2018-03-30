package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.RobotMap.CAN;
import org.usfirst.frc.team6560.robot.commands.climber.Climb;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**d
 *
 */
public class Climber extends Subsystem {
	WPI_TalonSRX climbMotor = new WPI_TalonSRX(CAN.CLIMBER);

	public Climber() {
		climbMotor.setSafetyEnabled(false);
	}
	
	/**
	 * sets the climbMotor to which ever parameter is greater in magnitude
	 * @param speedUp
	 * @param speedDown
	 */
	public void climb(double speedOne, double speedTwo) {
		if(speedOne > speedTwo)
			climbMotor.set(speedOne);
		else
			climbMotor.set(-1 * speedTwo);
	}

	public void stopClimber() {
		climbMotor.stopMotor();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}
}
