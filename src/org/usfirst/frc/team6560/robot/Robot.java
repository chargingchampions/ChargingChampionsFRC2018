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

import org.usfirst.frc.team6560.robot.commands.auto.CenterScale;
import org.usfirst.frc.team6560.robot.commands.auto.CenterSwitch;
import org.usfirst.frc.team6560.robot.commands.auto.CenterSwitchScale;
import org.usfirst.frc.team6560.robot.commands.auto.LeftScale;
import org.usfirst.frc.team6560.robot.commands.auto.LeftSwitch;
import org.usfirst.frc.team6560.robot.commands.auto.LeftSwitchScale;
import org.usfirst.frc.team6560.robot.commands.auto.RightScale;
import org.usfirst.frc.team6560.robot.commands.auto.RightSwitch;
import org.usfirst.frc.team6560.robot.commands.auto.RightSwitchScale;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team6560.robot.subsystems.*;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static VisionNetworkTables visionNetworkTables;
	public static Grabber grabber;
	public static Arm arm;
	public static CubeIntake cubeIntake;
	public static Climber climber;
	
	public static UsbCamera topviewCamera;
	public static UsbCamera downviewCamera;

	// remove the following if it causes a NetworkTable exception
	public static Preferences prefs;

	public static double grabberPVal, grabberIVal, grabberDVal, grabberAbsTol, armPVal, armIVal, armDVal, armAbsTol,
			drivePVal, driveIVal, driveDVal, driveAbsTol, driveRotatePVal, driveRotateAbsTol, driveRotateIVal,
			driveRotateDVal, grabberSafetySetpoint, grabberIntakeSetpoint, grabberSwitchSetpoint, grabberScaleSetpoint,
			armIntakeSetpoint, armSwitchSetpoint, armScaleSetpoint;

	public static int grabberLowerSoftLimit, armUpperSoftLimit;

	Command autonomousCommand;
	SendableChooser<Integer> chooser;

	public void robotInit() {
		// remove the following if it causes a NetworkTable exception
		// Putting and loading preferences
		prefs = Preferences.getInstance();
		putPrefsNumbers();
		initializePrefs();
		chooser = new SendableChooser<>();
		// initializing subsystems
		drive = new Drive();
		visionNetworkTables = new VisionNetworkTables();
		grabber = new Grabber();
		arm = new Arm();
		cubeIntake = new CubeIntake();
		oi = new OI();
		climber = new Climber();
		topviewCamera = CameraServer.getInstance().startAutomaticCapture();
		downviewCamera = CameraServer.getInstance().startAutomaticCapture();

		// adding auto options
		chooser.addObject("left station switch", new Integer(1));
		chooser.addObject("left station scale", new Integer(2));
		chooser.addObject("left station switch + scale", new Integer(3));
		chooser.addObject("center station switch", new Integer(4));
		chooser.addObject("center station scale", new Integer(5));
		chooser.addObject("center station switch + scale", new Integer(6));
		chooser.addObject("right station switch", new Integer(7));
		chooser.addObject("right station scale", new Integer(8));
		chooser.addObject("right station switch + scale", new Integer(9));
		chooser.addObject("do nothing", new Integer(10));
		chooser.addObject("drive straight", new Integer(11));
		SmartDashboard.putData("Auto station chooser", chooser);

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
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		int automode = chooser.getSelected().intValue();
		switch (automode) {
		case 1:
			// left station switch only
			autonomousCommand = new LeftSwitch(gameData);
		case 2:
			// left station scale only
			autonomousCommand = new LeftScale(gameData);
		case 3:
			// left station switch and scale
			autonomousCommand = new LeftSwitchScale(gameData);
		case 4:
			// center station switch only
			autonomousCommand = new CenterSwitch(gameData);
		case 5:
			// center station scale only
			autonomousCommand = new CenterScale(gameData);
		case 6:
			// center station switch and scale
			autonomousCommand = new CenterSwitchScale(gameData);
		case 7:
			// right station switch only
			autonomousCommand = new RightSwitch(gameData);
		case 8:
			// right station scale only
			autonomousCommand = new RightScale(gameData);
		case 9:
			// right station switch and scale
			autonomousCommand = new RightSwitchScale(gameData);
		case 10:
			// do nothing
		case 11:
			// drive straight
		}
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
		arm.refreshSubsystem();
		grabber.refreshSubsystem();
		initializePrefs();
		// TODO: update the PID vals and other stuff in initialization of subsystems,
		// which only occurs upon turning on the robot
	}

	public void teleopPeriodic() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		double distance = SmartDashboard.getNumber("distance", 0.0);
		double speed = SmartDashboard.getNumber("speed", 0.0);
		double angleToTurnTo = SmartDashboard.getNumber("angle to turn to", 0.0);
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Global Drive Speed Teleop Periodic", Drive.globalDriveSpeed);
		SmartDashboard.putNumber("Grabber Encoder Relative Position", Robot.grabber.getPosition());
		SmartDashboard.putNumber("Arm Encoder Relative Position", Robot.arm.getPosition());
		SmartDashboard.putNumber("Gyro angle", drive.getGyroAngle());
		SmartDashboard.putNumber("Left Encoder", drive.drive_enc_left.getDistance());
		SmartDashboard.putNumber("Right Encoder", drive.drive_enc_right.getDistance());

		SmartDashboard.putData("Drive Straight to Distance", new DriveStraightToDistance(distance, speed));
		SmartDashboard.putData("Turn To Angle", new TurnToAngle(angleToTurnTo, speed));
		
		SmartDashboard.putData("Left Switch", new LeftSwitch(gameData));
		SmartDashboard.putData("Right Switch", new RightSwitch(gameData));
		SmartDashboard.putData("Center Switch", new CenterSwitch(gameData));
		// SmartDashboard.putData("Drive Straight to Distance PID", new
		// PIDDriveStraightToDistance(distance, speed));
		// SmartDashboard.putData("Turn To Angle PID", new PIDTurnToAngle(angleToTurnTo,
		// speed));
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

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
		prefs.putDouble("Arm Scale Setpoint", prefs.getDouble("Arm Switch Setpoint", 0));
		prefs.putDouble("Arm Switch Setpoint", prefs.getDouble("Arm Scale Setpoint", 27025.0));

		prefs.putInt("Grabber Lower Soft Limit", prefs.getInt("Grabber Lower Soft Limit", 1300));
		prefs.putInt("Arm Upper Soft Limit", prefs.getInt("Arm Upper Soft Limit", 40000));

	}

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
		armSwitchSetpoint = prefs.getDouble("Arm Switch Setpoint", 0);
		armScaleSetpoint = prefs.getDouble("Arm Scale Setpoint", 27025.0);

		grabberLowerSoftLimit = prefs.getInt("Grabber Lower Soft Limit", 1300);
		armUpperSoftLimit = prefs.getInt("Arm Upper Soft Limit", 40000);
	}
}
