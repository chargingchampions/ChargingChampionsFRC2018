package org.usfirst.frc.team6560.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static class CAN {
		public static final int DRIVE_FRONTLEFT = 9;
		public static final int DRIVE_REARLEFT = 10;
		public static final int DRIVE_FRONTRIGHT = 4;
		public static final int DRIVE_REARRIGHT = 5;
		public static final int GRABBER_ROTATION = 1;
		public static final int GRABBER_RIGHT = 2;
		public static final int GRABBER_LEFT = 6;
		public static final int ARM1 = 8;
		public static final int ARM2 = 3;
		public static final int CLIMBER = 7;
		public static final int SECONDCLIMBER = 11;

		public static final int SOLENOID_0 = 0;
		public static final int SOLENOID_1 = 1;
		public static final int SOLENOID_2 = 2;
		public static final int SOLENOID_3 = 3;
		public static final int SOLENOID_4 = 4;
		public static final int SOLENOID_5 = 5;
		public static final int SOLENOID_6 = 6;
		public static final int SOLENOID_7 = 7;
		public static final int COMPRESSOR_ID = 0;
	}

	public static class Joysticks {
		public static final int GAMEPAD = 0;
		public static final int LOGITECH_JOYSTICK_ID = 1;

		// Gamepad buttons
		public static final int A_BUTTON = 1;
		public static final int B_BUTTON = 2;
		public static final int X_BUTTON = 3;
		public static final int Y_BUTTON = 4;
		public static final int LEFT_INDEX_BUTTON = 5;
		public static final int RIGHT_INDEX_BUTTON = 6;
		public static final int BACK_BUTTON = 7;
		public static final int START_BUTTON = 8;
		public static final int LEFT_AXIS_BUTTON = 9;
		public static final int RIGHT_AXIS_BUTTON = 10;

		// Gamepad axes
		public static final int LEFT_X_AXIS = 0;
		public static final int LEFT_Y_AXIS = 1;
		public static final int LEFT_TRIGGER = 2;
		public static final int RIGHT_TRIGGER = 3;
		public static final int RIGHT_X_AXIS = 4;
		public static final int RIGHT_Y_AXIS = 5;

		// Secondary Controller Buttons
		public static final int SECOND_TRIGGER_BUTTON = 1;
		public static final int SECOND_RIGHT_THUMB_BUTTON = 2;
		public static final int SECOND_BUTTON_3 = 3;
		public static final int SECOND_BUTTON_4 = 4;
		public static final int SECOND_BUTTON_5 = 5;
		public static final int SECOND_BUTTON_6 = 6;
		public static final int SECOND_BUTTON_7 = 7;
		public static final int SECOND_BUTTON_8 = 8;
		public static final int SECOND_BUTTON_9 = 9;
		public static final int SECOND_BUTTON_10 = 10;
		public static final int SECOND_BUTTON_11 = 11;
		public static final int SECOND_BUTTON_12 = 12;

		// Secondary Controller Axes
		public static final int SECOND_X_AXIS = 0;
		public static final int SECOND_Y_AXIS = 1;
		public static final int SECOND_Z_AXIS = 2;
		public static final int SECOND_SLIDER = 3;
	}
}
