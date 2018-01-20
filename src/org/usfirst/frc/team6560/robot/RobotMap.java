package org.usfirst.frc.team6560.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Joysticks {
		public static final int LOGITECH_JOYSTICK_ID = 1;

		//Button IDs
		public static final int TRIGGER_BUTTON = 1;
		public static final int RIGHT_THUMB_BUTTON = 2;
		public static final int BUTTON_3 = 3;
		public static final int BUTTON_4 = 4;
		public static final int BUTTON_5 = 5;
		public static final int BUTTON_6 = 6;
		public static final int BUTTON_7 = 7;
		public static final int BUTTON_8 = 8;
		public static final int BUTTON_9 = 9;
		public static final int BUTTON_10 = 10;
		public static final int BUTTON_11 = 11;
		public static final int BUTTON_12 = 12;
		
		//Axes IDs
		public static final int X_AXIS = 0;
		public static final int Y_AXIS = 1;
		public static final int Z_AXIS = 2;
		public static final int SLIDER = 3;
	}
	
	public static class CAN {
		public static final int DRIVE_FRONTLEFT = 0;
		public static final int DRIVE_REARLEFT = 0;
		public static final int DRIVE_FRONTRIGHT = 0;
		public static final int DRIVE_REARRIGHT = 0;
		
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
}
