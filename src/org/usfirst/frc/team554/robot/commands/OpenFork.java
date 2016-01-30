package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenFork extends Command {

    public OpenFork() {
    	requires(Robot.fork);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if( ! Robot.fork.atLeftOutLimit()){
    		Robot.fork.forkLeft(.5);
    	}
    	else{
    		Robot.fork.forkStopLeft();
    	}
    	if(! Robot.fork.atRightOutLimit()){
    	    Robot.fork.forkRight(.5);
    	}
    	else{
    		Robot.fork.forkStopRight();
    	}
    }
    
 
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((!Robot.oi.getJoystickOperator().getRawButton(8)) ||
    	    (Robot.fork.atRightOutLimit() && Robot.fork.atLeftOutLimit()));
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