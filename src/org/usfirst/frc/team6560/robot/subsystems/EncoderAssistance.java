package org.usfirst.frc.team6560.robot.subsystems;


import org.usfirst.frc.team6560.robot.commands.encoderAssistance.UseEncoderAssistance;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EncoderAssistance extends Subsystem {

	public double armScalar, driveScalar, grabberScalar;
	private double armScalarNormal, driveScalarNormal, grabberScalarNormal;
	private double armScalarSlow, driveScalarSlow, grabberScalarSlow;
	private double armScalarSlower, driveScalarSlower, grabberScalarSlower;
	public boolean activated = false;

	public EncoderAssistance() {
		armScalarNormal = 1.0;
		driveScalarNormal = 1.0;
		grabberScalarNormal = 1.0;
		
		setNormal();
		
		armScalarSlow = 0.4;
		driveScalarSlow = 0.6;
		grabberScalarSlow = 0.8;

		armScalarSlower = 0.0;
		driveScalarSlower = 0.6;
		grabberScalarSlower = 0.6;
	}
	
	public void setSlow() {
		activated = true;
		armScalar = armScalarSlow;
		driveScalar = driveScalarSlow;
		grabberScalar = grabberScalarSlow;
	}
	
	public void setNormal() {
		activated = false;
		armScalar = armScalarNormal;
		driveScalar = driveScalarNormal;
		grabberScalar = grabberScalarNormal;
	}
	
	public void setSlower() {
		activated = true;
		armScalar = armScalarSlower;
		driveScalar = driveScalarSlower;
		grabberScalar = grabberScalarSlower;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new UseEncoderAssistance());
    }
}

