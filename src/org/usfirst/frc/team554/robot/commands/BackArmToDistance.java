package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BackArmToDistance extends Command {
	
	private double TargetAngle;
	private double MoveSpeed;
	private double CurrentAngle;

    public BackArmToDistance(double TargetAngle,double MoveSpeed) {
    	requires(Robot.backarm);
    	this.TargetAngle = TargetAngle;
    	this.MoveSpeed = MoveSpeed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
 	
    		Robot.backarm.moveBackArmManually(MoveSpeed);    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	CurrentAngle = Robot.backarm.getBackArmSensorVal();
    	return ((MoveSpeed > 0 && CurrentAngle <= TargetAngle) ||
         	   (MoveSpeed < 0 && CurrentAngle >= TargetAngle));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.backarm.BackArmStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
