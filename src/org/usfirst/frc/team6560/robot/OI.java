package org.usfirst.frc.team6560.robot;

import org.usfirst.frc.team6560.robot.RobotMap.Joysticks;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetDefault;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetIntake;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetScale;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetSwitch;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.ShootCube;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.StartIntake;
import org.usfirst.frc.team6560.robot.commands.drive.DecreaseDriveSpeed;
import org.usfirst.frc.team6560.robot.commands.drive.IncreaseDriveSpeed;
import org.usfirst.frc.team6560.robot.commands.drive.TankDriveStraight;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick gamepad;
	public final Joystick logitechJoystick;

	public OI() {
		gamepad = new Joystick(Joysticks.GAMEPAD);
		logitechJoystick = new Joystick(Joysticks.LOGITECH_JOYSTICK_ID);

		JoystickButton aButton = new JoystickButton(gamepad, Joysticks.A_BUTTON);
		JoystickButton bButton = new JoystickButton(gamepad, Joysticks.B_BUTTON);
		JoystickButton xButton = new JoystickButton(gamepad, Joysticks.X_BUTTON);
		JoystickButton yButton = new JoystickButton(gamepad, Joysticks.Y_BUTTON);
		JoystickButton leftIndex = new JoystickButton(gamepad, Joysticks.LEFT_INDEX_BUTTON);
		JoystickButton rightIndex = new JoystickButton(gamepad, Joysticks.RIGHT_INDEX_BUTTON);
		JoystickButton backButton = new JoystickButton(gamepad, Joysticks.BACK_BUTTON);
		JoystickButton startButton = new JoystickButton(gamepad, Joysticks.START_BUTTON);
		JoystickButton leftAxisButton = new JoystickButton(gamepad, Joysticks.LEFT_AXIS_BUTTON);
		JoystickButton rightAxisButton = new JoystickButton(gamepad, Joysticks.RIGHT_AXIS_BUTTON);

		JoystickButton secondTrigger = new JoystickButton(logitechJoystick, Joysticks.SECOND_TRIGGER_BUTTON);
		JoystickButton secondRightThumb = new JoystickButton(logitechJoystick, Joysticks.SECOND_RIGHT_THUMB_BUTTON);
		JoystickButton secondButton3 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_3);
		JoystickButton secondButton4 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_4);
		JoystickButton secondButton5 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_5);
		JoystickButton secondButton6 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_6);
		JoystickButton secondButton7 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_7);
		JoystickButton secondButton8 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_8);
		JoystickButton secondButton9 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_9);
		JoystickButton secondButton10 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_10);
		JoystickButton secondButton11 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_11);
		JoystickButton secondButton12 = new JoystickButton(logitechJoystick, Joysticks.SECOND_BUTTON_12);

		// secondButton4.whileHeld(new ShootCube());

		// drive buttons
		yButton.whileHeld(new TankDriveStraight(0.5));
		aButton.whileHeld(new TankDriveStraight(-0.5));
		bButton.whileHeld(new IncreaseDriveSpeed());
		xButton.whileHeld(new DecreaseDriveSpeed());

		// arm rotation buttons

		// grabber rotation buttons
		secondButton3.whenPressed(new PIDSetIntake());
		secondButton4.whenPressed(new PIDSetSwitch());
		secondButton6.whenPressed(new PIDSetScale());
		secondButton5.whenPressed(new PIDSetDefault());
		// intake buttons
		secondRightThumb.whileHeld(new StartIntake());
		rightIndex.whileHeld(new StartIntake());
		leftIndex.whileHeld(new ShootCube());
		secondTrigger.whileHeld(new ShootCube());

		// secondTrigger.whileHeld(new IntakeCube());
		// rightThumb.whileHeld(new IntakeCube(1.0));
		// button4.whileHeld(new ShootCube(0.5));

	}

	// Axis
	public double getLeftXAxis() {
		return gamepad.getRawAxis(Joysticks.LEFT_X_AXIS);
	}

	public double getLeftYAxis() {
		return gamepad.getRawAxis(Joysticks.LEFT_Y_AXIS);
	}

	public double getLeftTrigger() {
		return gamepad.getRawAxis(Joysticks.LEFT_TRIGGER);
	}

	public double getRightTrigger() {
		return gamepad.getRawAxis(Joysticks.RIGHT_TRIGGER);
	}

	public double getRightXAxis() {
		return gamepad.getRawAxis(Joysticks.RIGHT_X_AXIS);
	}

	public double getRightYAxis() {
		return gamepad.getRawAxis(Joysticks.RIGHT_Y_AXIS);
	}

	public int getPOV() {
		return gamepad.getPOV();
	}

	public double getSecondXAxis() {
		return logitechJoystick.getRawAxis(Joysticks.SECOND_X_AXIS);
	}

	public double getSecondYAxis() {
		return logitechJoystick.getRawAxis(Joysticks.SECOND_Y_AXIS);
	}

	public double getSecondZAxis() {
		return logitechJoystick.getRawAxis(Joysticks.SECOND_Z_AXIS);
	}

	public double getSecondSlider() {
		return logitechJoystick.getRawAxis(Joysticks.SECOND_SLIDER);
	}

	public int getSecondPOV() {
		return logitechJoystick.getPOV();
	}

}
