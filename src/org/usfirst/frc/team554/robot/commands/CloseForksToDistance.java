package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseForksToDistance extends Command {
	
	private double TargetDistance;

    public CloseForksToDistance(double TargetDistance) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.fork);
    	this.TargetDistance = TargetDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double remainingDist;
    	
    	remainingDist = (Robot.fork.getDistanceBetweenForks() - this.TargetDistance)/2.;
    	if (remainingDist < 3.0) {
        	Robot.fork.forkLeft((-.4/3.)*remainingDist-.1);
        	Robot.fork.forkRight((-.4/3.)*remainingDist-.1);
    	}
    	else {
    		Robot.fork.forkLeft(-.5);
    		Robot.fork.forkRight(-.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((Robot.fork.getDistanceBetweenForks() <= TargetDistance) ||
    	      Robot.fork.atLeftInLimit() || Robot.fork.atRightInLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.fork.forkStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
