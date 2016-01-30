package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftLeft extends Command {
	
	private double DistanceTarget;
	private double DistanceCurrent;
	private double DistanceError;
		
    public ShiftLeft() {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.fork);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        DistanceTarget = Robot.fork.getDistanceBetweenForks();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DistanceCurrent = Robot.fork.getDistanceBetweenForks();
    	DistanceError = DistanceTarget-DistanceCurrent;
    	Robot.fork.forkLeft(.5);
        Robot.fork.forkRight(-.5 + .02 * DistanceError);;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((!Robot.oi.getJoystickOperator().getRawButton(5)) ||
    			  Robot.fork.atLeftOutLimit() || Robot.fork.atRightInLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.fork.forkStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
