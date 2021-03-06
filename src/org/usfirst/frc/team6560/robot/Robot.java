package org.usfirst.frc.team6560.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;

import org.usfirst.frc.team6560.robot.commands.ResetArmAndGrabberEncoders;
import org.usfirst.frc.team6560.robot.commands.auto.CenterTimeTime;
import org.usfirst.frc.team6560.robot.commands.auto.LeftTimeTime;
import org.usfirst.frc.team6560.robot.commands.auto.RightTimeTime;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightTime;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngleTime;
import org.usfirst.frc.team6560.robot.subsystems.*;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static Grabber grabber;
	public static ArmBasic armBasic;
	public static CubeIntake cubeIntake;
	public static Climber climber;
	public static SecondClimber secondClimber;
	public static EncoderAssistance encoderAssistance;

	public static UsbCamera topviewCamera;
	public static UsbCamera downviewCamera;

	// remove the following if it causes a NetworkTable exception
	public static Preferences prefs;

	/**
	public static double grabberPVal, grabberIVal, grabberDVal, grabberAbsTol, armPVal, armIVal, armDVal, armAbsTol,
			drivePVal, driveIVal, driveDVal, driveAbsTol, driveRotatePVal, driveRotateAbsTol, driveRotateIVal,
			driveRotateDVal, grabberSafetySetpoint, grabberIntakeSetpoint, grabberSwitchSetpoint, grabberScaleSetpoint,
			armIntakeSetpoint, armHighSafetySetpoint, armSwitchSetpoint, armScaleSetpoint;
	**/
	
	public static double encoderAssistanceSafety = 17375, encoderAssistanceMax = 27166, encoderGrabberSafety = 683;

	Command autonomousCommand;
	SendableChooser<Integer> chooser;

	public void robotInit() {
		// remove the following if it causes a NetworkTable exception
		// Putting and loading preferences
		prefs = Preferences.getInstance();
		//putPrefsNumbers();
		//initializePrefs();
		chooser = new SendableChooser<Integer>();
		// initializing subsystems
		drive = new Drive();
		grabber = new Grabber();
		armBasic = new ArmBasic();
		cubeIntake = new CubeIntake();
		climber = new Climber();
		secondClimber = new SecondClimber();
		encoderAssistance = new EncoderAssistance();
		oi = new OI();

		topviewCamera = CameraServer.getInstance().startAutomaticCapture();
		downviewCamera = CameraServer.getInstance().startAutomaticCapture();

		chooser.addDefault("Drive Straight", 0);
		chooser.addObject("LeftTimeTime", 1);
		chooser.addObject("CenterTimeTime", 2);
		chooser.addObject("RightTimeTime", 3);
		SmartDashboard.putData(chooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		grabber.refreshSubsystem();
		armBasic.resetEncoder();
		drive.initializeEncoders();
		//initializePrefs();

		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println(gameData);
		
		int chooserNum = chooser.getSelected().intValue();
		System.out.println("The chooser is set to: " + chooserNum);
		
		double driveScalar = 1.0;
		double rotateScalar = 1.0;
		
		switch (chooserNum) {
		case 0: {
			System.out.println("Setting autonomous command to DriveStraightTime");
			autonomousCommand = new DriveStraightTime(2.3*driveScalar, 0.7);
			break;
		}

		case 1: {
			System.out.println("Setting autonomous command to LeftTimeTime");
			autonomousCommand = new LeftTimeTime(gameData, driveScalar, rotateScalar);
			break;
		}

		case 2: {
			System.out.println("Setting autonomous command to CenterTimeTime");
			autonomousCommand = new CenterTimeTime(gameData, driveScalar, rotateScalar);
			break;
		} 

		case 3: {
			System.out.println("Setting autonomous command to RightTimeTime");
			autonomousCommand = new RightTimeTime(gameData, driveScalar, rotateScalar);
			break;
		}

		default: {
			System.out.println("Setting autonomous command to DriveStraightTime");
			autonomousCommand = new DriveStraightTime(1.5*driveScalar, 0.7);
			break;
		}

		}

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		// TODO: delete the below when ready
		//putTuningToolsValues();
		// TODO: update the PID vals and other stuff in initialization of subsystems,
		// which only occurs upon turning on the robot
	}

	public void teleopPeriodic() {
		// TODO: delete the below method once ready
		//tuningTools();
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Global Drive Speed Teleop Periodic", Drive.globalDriveSpeed);
		SmartDashboard.putBoolean("Encoder Assistance", encoderAssistance.activated);
		SmartDashboard.putNumber("Arm Scalar", encoderAssistance.armScalar);
		SmartDashboard.putNumber("Drive Scalar", encoderAssistance.driveScalar);
		SmartDashboard.putNumber("Grabber Scalar", encoderAssistance.grabberScalar);
		SmartDashboard.putNumber("Arm Safety", encoderAssistanceSafety);
		SmartDashboard.putNumber("Arm Max", encoderAssistanceMax);
		SmartDashboard.putNumber("Current Arm Position", armBasic.getEncoderPosition());
		SmartDashboard.putNumber("Current Grabber Position", grabber.getEncoderPosition());
		SmartDashboard.putData("Refresh Subsystems", new ResetArmAndGrabberEncoders());

	}

	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	public void putPrefsNumbers() {
		// insert preference values here
		prefs.putDouble("Grabber P Value", prefs.getDouble("Grabber P Value", 0.001));
		prefs.putDouble("Grabber I Value", prefs.getDouble("Grabber I Value", 0.0));
		prefs.putDouble("Grabber D Value", prefs.getDouble("Grabber D Value", 0.0));
		prefs.putDouble("Grabber Absolute Tolerance", prefs.getDouble("Grabber Absolute Tolerance", 100));

		prefs.putDouble("Arm P Value", prefs.getDouble("Arm P Value", 0.007));
		prefs.putDouble("Arm I Value", prefs.getDouble("Arm I Value", 0.0));
		prefs.putDouble("Arm D Value", prefs.getDouble("Arm D Value", 0.0));
		prefs.putDouble("Arm Absolute Tolerance", prefs.getDouble("Arm Absolute Tolerance", 500));

		prefs.putDouble("Drive P Value", prefs.getDouble("Drive P Value", 0.7));
		prefs.putDouble("Drive I Value", prefs.getDouble("Drive I Value", 0.0));
		prefs.putDouble("Drive D Value", prefs.getDouble("Drive D Value", 0.0));
		prefs.putDouble("Drive Absolute Tolerance", prefs.getDouble("Drive Absolute Tolerance", 2));

		prefs.putDouble("Drive Rotate P Value", prefs.getDouble("Drive Rotate P Value", 0.7));
		prefs.putDouble("Drive Rotate I Value", prefs.getDouble("Drive Rotate I Value", 0.0));
		prefs.putDouble("Drive Rotate D Value", prefs.getDouble("Drive Rotate D Value", 0.0));
		prefs.putDouble("Drive Rotate Absolute Tolerance", prefs.getDouble("Drive Rotate I Value", 2));

		prefs.putDouble("Grabber Safety Setpoint", prefs.getDouble("Grabber Safety Setpoint", 900));
		prefs.putDouble("Grabber Intake Setpoint", prefs.getDouble("Grabber Intake Setpoint", 2389));
		prefs.putDouble("Grabber Switch Setpoint", prefs.getDouble("Grabber Switch Setpoint", 926));
		prefs.putDouble("Grabber Scale Setpoint", prefs.getDouble("Grabber Scale Setpoint", 4238));

		prefs.putDouble("Arm Intake Setpoint", prefs.getDouble("Arm Intake Setpoint", 0));
		prefs.putDouble("Arm High Safety Setpoint", prefs.getDouble("Arm High Safety Setpoint", 20000));
		prefs.putDouble("Arm Scale Setpoint", prefs.getDouble("Arm Switch Setpoint", 0));
		prefs.putDouble("Arm Switch Setpoint", prefs.getDouble("Arm Scale Setpoint", 27025.0));

	}
	**/

	/**
	public void initializePrefs() {
		grabberPVal = prefs.getDouble("Grabber P Value", 0.001);
		grabberIVal = prefs.getDouble("Grabber I Value", 0.0);
		grabberDVal = prefs.getDouble("Grabber D Value", 0.0);
		grabberAbsTol = prefs.getDouble("Grabber Absolute Tolerance", 100);

		armPVal = prefs.getDouble("Arm P Value", 0.007);
		armIVal = prefs.getDouble("Arm I Value", 0.0);
		armDVal = prefs.getDouble("Arm D Value", 0.0);
		armAbsTol = prefs.getDouble("Arm Absolute Tolerance", 500);

		drivePVal = prefs.getDouble("Drive P Value", 0.7);
		driveIVal = prefs.getDouble("Drive I Value", 0.0);
		driveDVal = prefs.getDouble("Drive D Value", 0.0);
		driveAbsTol = prefs.getDouble("Drive Absolute Tolerance", 2);

		driveRotatePVal = prefs.getDouble("Drive Rotate P Value", 0.7);
		driveRotateIVal = prefs.getDouble("Drive Rotate I Value", 0.0);
		driveRotateDVal = prefs.getDouble("Drive Rotate D Value", 0.0);
		driveRotateAbsTol = prefs.getDouble("Drive Rotate Absolute Tolerance", 2);

		grabberSafetySetpoint = prefs.getDouble("Grabber Safety Setpoint", 900);
		grabberIntakeSetpoint = prefs.getDouble("Grabber Intake Setpoint", 2389);
		grabberSwitchSetpoint = prefs.getDouble("Grabber Switch Setpoint", 926);
		grabberScaleSetpoint = prefs.getDouble("Grabber Scale Setpoint", 4238);

		armIntakeSetpoint = prefs.getDouble("Arm Intake Setpoint", 0);
		armHighSafetySetpoint = prefs.getDouble("Arm High Safety Setpoint", 20000);
		armSwitchSetpoint = prefs.getDouble("Arm Switch Setpoint", 0);
		armScaleSetpoint = prefs.getDouble("Arm Scale Setpoint", 27025.0);

	}
	**/

	public void tuningTools() {
		double distance = SmartDashboard.getNumber("distance", 0.0);
		double speed = SmartDashboard.getNumber("speed", 0.0);
		double angleToTurnTo = SmartDashboard.getNumber("angle to turn to", 0.0);
		double time = SmartDashboard.getNumber("time to run", 0.0);

		SmartDashboard.putNumber("Grabber Encoder Relative Position", Robot.grabber.getEncoderPosition());
		SmartDashboard.putNumber("Gyro angle", drive.getGyroAngle());
		SmartDashboard.putNumber("Left Encoder", drive.drive_enc_left.getDistance());
		SmartDashboard.putNumber("Right Encoder", drive.drive_enc_right.getDistance());
		SmartDashboard.putNumber("Abs Distance", drive.getAbsDistance());
		SmartDashboard.putNumber("Displacement", drive.getDisplacement());

		SmartDashboard.putData("Drive Straight to Distance", new DriveStraightToDistance(distance, speed));
		SmartDashboard.putData("Drive Straight Time", new DriveStraightTime(time, speed));
		SmartDashboard.putData("Turn To Angle", new TurnToAngle(angleToTurnTo, speed));
		SmartDashboard.putData("Turn To Angle Time", new TurnToAngleTime(time, speed));
		SmartDashboard.putData("Drive Straight Timed", new DriveStraightTime(time, speed));
		SmartDashboard.putData("Refresh Subsystems", new ResetArmAndGrabberEncoders());

		// These PID commands are causing crashes because of outOfMemory threads...
		// SmartDashboard.putData("PID Drive Straight to Distance", new
		// PIDDriveStraightToDistance(distance, speed));
		// SmartDashboard.putData("PID Turn To Angle", new PIDTurnToAngle(angleToTurnTo,
		// speed));

	}

	public void putTuningToolsValues() {
		SmartDashboard.putNumber("distance", 0.0);
		SmartDashboard.putNumber("speed", 0.0);
		SmartDashboard.putNumber("angle to turn to", 0.0);
		SmartDashboard.putNumber("time to run", 0.0);
	}
}
