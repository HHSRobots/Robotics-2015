package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team554.robot.commands.MoveElevator;
/**
 *
 */
public class Elevator extends Subsystem {
	private SpeedController ElevatorMotor;
	private DigitalInput ElevatorBottomLimit, ElevatorTopLimit;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Elevator() {
		
		super();
		ElevatorMotor = new Talon(4);
		ElevatorBottomLimit = new DigitalInput(0);
		ElevatorTopLimit = new DigitalInput(1);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new MoveElevator());
    }
    
   
    public void moveElevatorJoystick(Joystick operator_joystick){
    	if ((operator_joystick.getY() >.1) && getBotLimitVal()){
    		ElevatorMotor.set(operator_joystick.getY()*.4); // use joystick value but limit to 40% going down
    		//
    	}
    	else if (operator_joystick.getY() <-.01 && getTopLimitVal()){
    		ElevatorMotor.set(operator_joystick.getY()); // use joystick value
    	}
    	else {
    		ElevatorMotor.set(0.0);
    	}
    	
    }
    
    public void moveElevatorManually(double speed) {
    	ElevatorMotor.set(speed);
    }
     
    public void elevatorStop() {
    	
    	//Could do a while loop or 2 here to prevent sudden stops
    	
    	ElevatorMotor.set(0.0);
    	
    	//Stop the elevator
    	
    }
    
    public boolean getTopLimitVal() {
    	
    	return ElevatorTopLimit.get();
    	
    }
    
    public boolean getBotLimitVal() {
    	
    	return ElevatorBottomLimit.get();
    	
    }
    
    public boolean movingUp(){
    	if (ElevatorMotor.get() < 0.){
    		return true; 		
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean movingdown(){
    	if (ElevatorMotor.get() > 0.){
    		return true; 		
    	}
    	else{
    		return false;
    	}
    }
    
    public void log() {
        SmartDashboard.putBoolean("ElevatorBottom",getBotLimitVal());
        SmartDashboard.putBoolean("ElevatorTop",getTopLimitVal());
    }
}

