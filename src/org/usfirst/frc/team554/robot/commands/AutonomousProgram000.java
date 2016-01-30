package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousProgram000 extends CommandGroup {
    
    public  AutonomousProgram000() {
    	
    	//requires(Robot.fork);
    	//requires(Robot.drivetrain);
    	//requires(Robot.elevator);

    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	
    	addSequential(new CloseForksToDistance(19.25));   // close forks to 19.5"
    	addSequential(new ElevatorTimedMove(1.0,-1.0));     // move elevator up
    	addSequential(new RobotMoveToDistance(-96.,-.6)); // move robot forward 175"
    	addSequential(new ElevatorTimedMove(1.6,.35));    // move elevator down to limit
    	addSequential(new OpenForksToDistance(24.));
    	addSequential(new RobotMoveToDistance(-2.,-.6)); // move robot back 2"
    }
}
