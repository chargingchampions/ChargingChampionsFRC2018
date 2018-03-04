package org.usfirst.frc.team6560.robot.commands.auto;

import org.usfirst.frc.team6560.robot.commands.PID.PIDSetIntake;
import org.usfirst.frc.team6560.robot.commands.PID.PIDSetSwitch;
import org.usfirst.frc.team6560.robot.commands.cubeIntake.OpenIntakeArms;
import org.usfirst.frc.team6560.robot.commands.drive.DriveStraightToDistance;
import org.usfirst.frc.team6560.robot.commands.drive.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightRight extends CommandGroup {

    public RightRight() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new PIDSetSwitch());
    	addSequential(new DriveStraightToDistance(120, 0.8));
    	addSequential(new TurnToAngle(90, 0.7));
    	addSequential(new DriveStraightToDistance(16, 0.8));
    	addSequential(new OpenIntakeArms(0.5));
    	addSequential(new DriveStraightToDistance(-16, 0.8));
    	addParallel(new PIDSetIntake());
    	addSequential(new TurnToAngle(-90, 0.7));
    	addSequential(new DriveStraightToDistance(30, 0.8));
    	addSequential(new TurnToAngle(90, 0.7));
    	addSequential(new DriveStraightToDistance(24, 0.8));
    	addParallel(new OpenIntakeArms(2));
    	addSequential(new DriveStraightToDistance(-20, 0.8));
    	addSequential(new PIDSetSwitch());
    	addSequential(new OpenIntakeArms(0.5));
    }
}
