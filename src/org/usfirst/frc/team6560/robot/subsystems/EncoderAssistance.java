package org.usfirst.frc.team6560.robot.subsystems;

import org.usfirst.frc.team6560.robot.commands.encoderAssistance.UseEncoderAssistance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EncoderAssistance extends Subsystem {

	public double armScalar, driveScalar, grabberScalar;
	public double armScalarNormal, driveScalarNormal, grabberScalarNormal;
	public double armScalarSlow, driveScalarSlow, grabberScalarSlow;

	public EncoderAssistance() {
		armScalarNormal = 1.0;
		driveScalarNormal = 1.0;
		grabberScalarNormal = 1.0;
		
		setNormal();
		
		armScalarSlow = 0.6;
		driveScalarSlow = 0.4;
		grabberScalarSlow = 0.8;
	}
	
	public void setSlow() {
		armScalar = armScalarSlow;
		driveScalar = driveScalarSlow;
		grabberScalar = grabberScalarSlow;
	}
	
	public void setNormal() {
		armScalar = armScalarNormal;
		driveScalar = driveScalarNormal;
		grabberScalar = grabberScalarNormal;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new UseEncoderAssistance());
    }
}

