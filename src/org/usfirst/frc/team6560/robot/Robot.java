package org.usfirst.frc.team6560.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6560.robot.subsystems.*;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static VisionNetworkTables visionNetworkTables;
	public static Grabber grabber;
	public static Arm arm;
	public static CubeIntake cubeIntake;
	
	
	//remove the following if it causes a NetworkTable exception
	public static Preferences prefs;
	
	public static double grabberPVal, grabberIVal, grabberDVal, grabberAbsTol, armPVal, armIVal, armDVal, armAbsTol,
	grabberSafetySetpoint, grabberIntakeSetpoint, grabberSwitchSetpoint, grabberScaleSetpoint,
	armIntakeSetpoint, armSwitchSetpoint, armScaleSetpoint,
	visionMotorSpeed, visionWaitTime, visionTolerance;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public void robotInit() {
		//remove the following if it causes a NetworkTable exception
		prefs = Preferences.getInstance();
		
		//insert preference values here
		grabberPVal = prefs.getDouble("Grabber P Value", 0.0);
		grabberIVal = prefs.getDouble("Grabber I Value", 0.0);
		grabberDVal = prefs.getDouble("Grabber D Value", 0.0);
		grabberAbsTol = prefs.getDouble("Grabber Absolute Tolerance", 1000);
		armPVal = prefs.getDouble("Arm P Value", 0.0);
		armIVal = prefs.getDouble("Arm I Value", 0.0);
		armDVal = prefs.getDouble("Arm D Value", 0.0);
		armAbsTol = prefs.getDouble("Arm Absolute Tolerance", 1000);
		
		armIntakeSetpoint = Robot.prefs.getDouble("Arm Intake Setpoint", 0);
		grabberSafetySetpoint = Robot.prefs.getDouble("Grabber Safety Setpoint", 4000);
		grabberIntakeSetpoint = Robot.prefs.getDouble("Grabber Intake Setpoint", 3000);
		armScaleSetpoint = Robot.prefs.getDouble("Arm Scale Setpoint", 10000);
		grabberScaleSetpoint = Robot.prefs.getDouble("Grabber Scale Setpoint", 3000);
		armSwitchSetpoint = Robot.prefs.getDouble("Arm Switch Setpoint", 5000);
		grabberSwitchSetpoint = Robot.prefs.getDouble("Grabber Switch Setpoint", 3000);
		
		visionMotorSpeed = prefs.getDouble("Vision Motor Speed", 0.7);
		visionWaitTime = prefs.getDouble("Vision Wait Time", 0.5);
		visionTolerance = prefs.getDouble("Vision Tolerance", 10);
		
		
		
		drive = new Drive();
		visionNetworkTables = new VisionNetworkTables();
		grabber = new Grabber();
		arm = new Arm();
		cubeIntake = new CubeIntake();
		oi = new OI();
		
		SmartDashboard.putData("Auto mode", chooser);
		
		LiveWindow.addSensor("Arm", "Quadrature Encoder", Robot.arm);
		LiveWindow.addSensor("Grabber", "Quadrature Encoder", Robot.grabber);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Global Drive Speed Teleop Periodic", Robot.drive.globalDriveSpeed);
		SmartDashboard.putNumber("Grabber Encoder Relative Position", Robot.grabber.getPosition());
		SmartDashboard.putNumber("Arm Encoder Relative Position", Robot.arm.getPosition());
	}

	public void testPeriodic() {
		LiveWindow.addSensor("Arm", "Quadrature Encoder", Robot.arm);
		LiveWindow.addSensor("Grabber", "Quadrature Encoder", Robot.grabber);
		LiveWindow.run();
	}
}
