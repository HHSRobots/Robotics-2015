package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorTimedMove extends Command {
	
	private double TimeoutValue;
	private double SpeedValue;

    public ElevatorTimedMove(double TimeoutValue, double SpeedValue) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
        this.TimeoutValue= TimeoutValue;
        this.SpeedValue = SpeedValue;
        setTimeout(this.TimeoutValue);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       //setTimeout(this.TimeoutValue);
    	Robot.elevator.moveElevatorManually(this.SpeedValue);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.elevator.moveElevatorManually(this.SpeedValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isTimedOut() || (Robot.elevator.movingdown() && !Robot.elevator.getBotLimitVal())
        		||( Robot.elevator.movingUp() && !Robot.elevator.getTopLimitVal()));
    }
    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.elevatorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
