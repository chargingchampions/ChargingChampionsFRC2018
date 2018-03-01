package org.usfirst.frc.team6560.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;

import org.usfirst.frc.team6560.robot.commands.auto.CenterLeft;
import org.usfirst.frc.team6560.robot.commands.auto.CenterRight;
import org.usfirst.frc.team6560.robot.commands.auto.LeftLeft;
import org.usfirst.frc.team6560.robot.commands.auto.LeftRight;
import org.usfirst.frc.team6560.robot.commands.auto.RightLeft;
import org.usfirst.frc.team6560.robot.commands.auto.RightRight;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.subsystems.*;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static VisionNetworkTables visionNetworkTables;
	public static Grabber grabber;
	public static Arm arm;
	public static CubeIntake cubeIntake;
	public static UsbCamera topviewCamera; 
	
	
	//remove the following if it causes a NetworkTable exception
	public static Preferences prefs;
	
	public static double grabberPVal, grabberIVal, grabberDVal, grabberAbsTol, armPVal, armIVal, armDVal, armAbsTol,
	drivePVal, driveIVal, driveDVal, driveAbsTol,
	grabberSafetySetpoint, grabberIntakeSetpoint, grabberSwitchSetpoint, grabberScaleSetpoint,
	armIntakeSetpoint, armSwitchSetpoint, armScaleSetpoint,
	visionMotorSpeed, visionWaitTime, visionTolerance;
	
	public static int grabberLowerSoftLimit, armUpperSoftLimit;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public void robotInit() {
		//remove the following if it causes a NetworkTable exception
		prefs = Preferences.getInstance();
		
		//insert preference values here
		prefs.putDouble("Grabber P Value", 0.0003);
		prefs.putDouble("Grabber I Value", 0.0);
		prefs.putDouble("Grabber D Value", 0.0);
		prefs.putDouble("Grabber Absolute Tolerance", 200);
		prefs.putDouble("Arm P Value", 0.0007);
		prefs.putDouble("Arm I Value", 0.0);
		prefs.putDouble("Arm D Value", 0.0);
		prefs.putDouble("Arm Absolute Tolerance", 500);
		
		prefs.putDouble("Drive P Value", 0.007);
		prefs.putDouble("Drive I Value", 0.0);
		prefs.putDouble("Drive D Value", 0.0);
		prefs.putDouble("Drive Absolute Tolerance", 2);
		
		
		prefs.putDouble("Arm Intake Setpoint", 0);
		prefs.putDouble("Grabber Safety Setpoint", 0);
		prefs.putDouble("Grabber Intake Setpoint", 3000);
		prefs.putDouble("Arm Scale Setpoint", 29805.0);
		prefs.putDouble("Grabber Scale Setpoint", 3000);
		prefs.putDouble("Arm Switch Setpoint", 0.0);
		prefs.putDouble("Grabber Switch Setpoint", 3000);
		
		prefs.putDouble("Vision Motor Speed", 0.7);
		prefs.putDouble("Vision Wait Time", 0.5);
		prefs.putDouble("Vision Tolerance", 10);
		
		prefs.putInt("Grabber Lower Soft Limit", 1300);
		prefs.putInt("Arm Upper Soft Limit", 40000);
		
		grabberPVal = prefs.getDouble("Grabber P Value", 0.0003);
		grabberIVal = prefs.getDouble("Grabber I Value", 0.0);
		grabberDVal = prefs.getDouble("Grabber D Value", 0.0);
		grabberAbsTol = prefs.getDouble("Grabber Absolute Tolerance", 1000);
		armPVal = prefs.getDouble("Arm P Value", 0.0007);
		armIVal = prefs.getDouble("Arm I Value", 0.0);
		armDVal = prefs.getDouble("Arm D Value", 0.0);
		armAbsTol = prefs.getDouble("Arm Absolute Tolerance", 1000);
		drivePVal = prefs.getDouble("Drive P Value", 0.007);
		driveIVal = prefs.getDouble("Drive I Value", 0.0);
		driveDVal = prefs.getDouble("Drive D Value", 0.0);
		driveAbsTol = prefs.getDouble("Drive Absolute Tolerance", 2);
		
		armIntakeSetpoint = prefs.getDouble("Arm Intake Setpoint", 0);
		grabberSafetySetpoint = prefs.getDouble("Grabber Safety Setpoint", 4000);
		grabberIntakeSetpoint = prefs.getDouble("Grabber Intake Setpoint", 3000);
		armScaleSetpoint = prefs.getDouble("Arm Scale Setpoint", 38305.0);
		grabberScaleSetpoint = prefs.getDouble("Grabber Scale Setpoint", 3000);
		armSwitchSetpoint = prefs.getDouble("Arm Switch Setpoint", 5000);
		grabberSwitchSetpoint = prefs.getDouble("Grabber Switch Setpoint", 3000);
		
		visionMotorSpeed = prefs.getDouble("Vision Motor Speed", 0.7);
		visionWaitTime = prefs.getDouble("Vision Wait Time", 0.5);
		visionTolerance = prefs.getDouble("Vision Tolerance", 10);
		
		grabberLowerSoftLimit = prefs.getInt("Grabber Lower Soft Limit", 1300);
		armUpperSoftLimit = prefs.getInt("Arm Upper Soft Limit", 40000);
		
		drive = new Drive();
		visionNetworkTables = new VisionNetworkTables();
		grabber = new Grabber();
		arm = new Arm();
		cubeIntake = new CubeIntake();
		oi = new OI();
		topviewCamera = CameraServer.getInstance().startAutomaticCapture();
		
		chooser.addObject("Left station - left switch", new LeftLeft());
		chooser.addObject("Left station - right switch", new LeftRight());
		chooser.addObject("Mid station - left switch", new CenterLeft());
		chooser.addObject("Mid station - right switch", new CenterRight());
		chooser.addObject("Right station - left switch", new RightLeft());
		chooser.addObject("Right station - right switch", new RightRight());
		chooser.addDefault("leftleft", new LeftLeft());
		SmartDashboard.putData("Auto mode", chooser);
		
		LiveWindow.addSensor("Arm", "Quadrature Encoder", Robot.arm);
		LiveWindow.addSensor("Grabber", "Quadrature Encoder", Robot.grabber);
		
		SmartDashboard.putNumber("distance", 0.0);
		SmartDashboard.putNumber("speed", 0.0);
		SmartDashboard.putNumber("angle to turn to", 0.0);
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
		arm.resetQuadraturePosition();
		grabber.resetQuadraturePosition();
		grabberPVal = prefs.getDouble("Grabber P Value", 0.0003);
		grabberIVal = prefs.getDouble("Grabber I Value", 0.0);
		grabberDVal = prefs.getDouble("Grabber D Value", 0.0);
		grabberAbsTol = prefs.getDouble("Grabber Absolute Tolerance", 1000);
		armPVal = prefs.getDouble("Arm P Value", 0.0007);
		armIVal = prefs.getDouble("Arm I Value", 0.0);
		armDVal = prefs.getDouble("Arm D Value", 0.0);
		armAbsTol = prefs.getDouble("Arm Absolute Tolerance", 1000);
		drivePVal = prefs.getDouble("Drive P Value", 0.007);
		driveIVal = prefs.getDouble("Drive I Value", 0.0);
		driveDVal = prefs.getDouble("Drive D Value", 0.0);
		driveAbsTol = prefs.getDouble("Drive Absolute Tolerance", 2);
		
		armIntakeSetpoint = prefs.getDouble("Arm Intake Setpoint", 0);
		grabberSafetySetpoint = prefs.getDouble("Grabber Safety Setpoint", 4000);
		grabberIntakeSetpoint = prefs.getDouble("Grabber Intake Setpoint", 3000);
		armScaleSetpoint = prefs.getDouble("Arm Scale Setpoint", 38305.0);
		grabberScaleSetpoint = prefs.getDouble("Grabber Scale Setpoint", 3000);
		armSwitchSetpoint = prefs.getDouble("Arm Switch Setpoint", 5000);
		grabberSwitchSetpoint = prefs.getDouble("Grabber Switch Setpoint", 3000);
		
		visionMotorSpeed = prefs.getDouble("Vision Motor Speed", 0.7);
		visionWaitTime = prefs.getDouble("Vision Wait Time", 0.5);
		visionTolerance = prefs.getDouble("Vision Tolerance", 10);
		
	}
	
	public void teleopPeriodic() {
		
		double distance = SmartDashboard.getNumber("distance", 0.0);
		double speed = SmartDashboard.getNumber("speed", 0.0);
		double angleToTurnTo = SmartDashboard.getNumber("angle to turn to", 0.0);
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Global Drive Speed Teleop Periodic", Drive.globalDriveSpeed);
		SmartDashboard.putNumber("Grabber Encoder Relative Position", Robot.grabber.getPosition());
		SmartDashboard.putNumber("Arm Encoder Relative Position", Robot.arm.getPosition());
		SmartDashboard.putData("Drive Straight to Distance", new DriveStraightToDistance(distance, speed, 1.0));
		SmartDashboard.putData("LeftLeftAuto", new LeftLeft());
		SmartDashboard.putNumber("Gyro angle", drive.gyro.getAngle());
		//SmartDashboard.putData("Auto LeftLeft", new LeftLeft());
		//THE ABOVE CAUSED THE MOTOR CONTROLLER ERROR... MAYBE? OR IT WAS THE TURN TO DRIVE METHOD IN DRIVE
		
	}

	public void testPeriodic() {
		LiveWindow.addSensor("Arm", "Quadrature Encoder", Robot.arm);
		LiveWindow.addSensor("Grabber", "Quadrature Encoder", Robot.grabber);
		LiveWindow.run();
	}
}
