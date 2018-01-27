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
	public static Pneumatics pneumatics;
	public static CubeIntake cubeIntake;
	public static Arm arm;
	
	//remove the following if it causes a NetworkTable exception
	public static Preferences prefs;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public void robotInit() {
		drive = new Drive();
		visionNetworkTables = new VisionNetworkTables();
		//pneumatics = new Pneumatics();
		cubeIntake = new CubeIntake();
		arm = new Arm();
		oi = new OI();
		
		SmartDashboard.putData("Auto mode", chooser);
		//remove the following if it causes a NetworkTable exception
		prefs = Preferences.getInstance();
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
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
