package org.usfirst.frc.team554.robot.subsystems;

import org.usfirst.frc.team554.robot.commands.MoveBackArm;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BackArm extends Subsystem {
    
	private SpeedController BackArmMotor;
	private Potentiometer BackArmAngleSensor; 
	private double BackArmLowerLimit, BackArmUpperLimit;
	
	public BackArm() {
		
		super();
		BackArmMotor = new Talon(5);
		BackArmAngleSensor = new AnalogPotentiometer(0);		
		
		BackArmUpperLimit = 0.91;
		BackArmLowerLimit = 0.66;
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new MoveBackArm());
    }
    
   
    public void moveBackArmJoystick(Joystick operator_joystick){
    	if ((operator_joystick.getRawAxis(3) >.1) && (getBackArmSensorVal() > BackArmLowerLimit)){
    		BackArmMotor.set(operator_joystick.getRawAxis(3)*.4); // use joystick value but limit to 40% going down
    		//
    	}
    	else if (operator_joystick.getRawAxis(3) <-.01 && (getBackArmSensorVal() < BackArmUpperLimit)){
    		BackArmMotor.set(operator_joystick.getRawAxis(3)); // use joystick value
    	}
    	else {
    		BackArmMotor.set(0.0);
    	}
    	
    }
    
    public void moveBackArmManually(double speed) {
    	BackArmMotor.set(speed);
    }
     
    public void BackArmStop() {
    	BackArmMotor.set(0.0);
    }
    
    public double getBackArmSensorVal() {
    	return BackArmAngleSensor.get();
     }
    
    
    public void log() {
        SmartDashboard.putNumber("BackArmAngle",getBackArmSensorVal());
    }
}

