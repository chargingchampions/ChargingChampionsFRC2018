package org.usfirst.frc.team6560.robot;

import org.usfirst.frc.team6560.robot.RobotMap.Joysticks;
import org.usfirst.frc.team6560.robot.commands.IntakeCube;
import org.usfirst.frc.team6560.robot.commands.OpenArms;
import org.usfirst.frc.team6560.robot.commands.ShootCube;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick logitechJoystick;
	
	public OI() {
		logitechJoystick = new Joystick(Joysticks.LOGITECH_JOYSTICK_ID);

		JoystickButton trigger = new JoystickButton(logitechJoystick, Joysticks.TRIGGER_BUTTON);
		JoystickButton rightThumb = new JoystickButton(logitechJoystick, Joysticks.RIGHT_THUMB_BUTTON);
		JoystickButton button3 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_3);
		JoystickButton button4 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_4);
		JoystickButton button5 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_5);
		JoystickButton button6 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_6);
		JoystickButton button7 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_7);
		JoystickButton button8 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_8);
		JoystickButton button9 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_9);
		JoystickButton button10 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_10);
		JoystickButton button11 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_11);
		JoystickButton button12 = new JoystickButton(logitechJoystick, Joysticks.BUTTON_12);
		
		//trigger.whileHeld(new OpenArms());
		//rightThumb.whileHeld(new IntakeCube(1.0));
		//button4.whileHeld(new ShootCube(0.5));
	}
	
	public double getXAxis() {
		return logitechJoystick.getRawAxis(Joysticks.X_AXIS);
	}

	public double getYAxis() {
		return logitechJoystick.getRawAxis(Joysticks.Y_AXIS);
	}

	public double getZAxis() {
		return logitechJoystick.getRawAxis(Joysticks.Z_AXIS);
	}

	public double getSlider() {
		return logitechJoystick.getRawAxis(Joysticks.SLIDER);
	}
}
