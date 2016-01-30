package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveRightForkIn extends Command {

    public MoveRightForkIn() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.fork);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
    	Robot.fork.forkRight(-.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ((!Robot.oi.getJoystickOperator().getRawButton(3)) ||Robot.fork.atRightInLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.fork.forkStopRight();
 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
