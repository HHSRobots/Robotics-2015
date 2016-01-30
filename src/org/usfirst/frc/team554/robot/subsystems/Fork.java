package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class Fork extends Subsystem {
    private SpeedController LeftForkMotor, RightForkMotor;
    private Encoder LeftForkEncoder, RightForkEncoder;
    double leftForkInnerLimit, leftForkOuterLimit, rightForkInnerLimit, rightForkOuterLimit;
    double DistanceBetweenForksOffset;
    
    
    public Fork(){
    	super();
    	RightForkMotor = new Talon(6);
    	LeftForkMotor = new Talon(7);
    	LeftForkEncoder = new Encoder(6,7);
    	RightForkEncoder = new Encoder(8,9);
    	
    	LeftForkEncoder.setDistancePerPulse(1./256.);
    	RightForkEncoder.setDistancePerPulse(1./256.);
    	DistanceBetweenForksOffset = 21.75;  // forks must be positioned evenly 22 inches apart before power on or reset
    	
    	//TODO  These limits are currently made up. Must be updated !!!!!!!!
    	leftForkInnerLimit = -5.5;
    	leftForkOuterLimit = 2.;
    	rightForkInnerLimit = -5.5;
    	rightForkOuterLimit = 2.;  	
    	
    	
    }
    
    public void initDefaultCommand() {
    }
    
    
    public void forkLeft(double speed){
    	LeftForkMotor.set(speed);
    }
    
    public void forkRight(double speed){
    	RightForkMotor.set(speed);
    }
    
    public void forkStop(){
    	LeftForkMotor.set(0);
    	RightForkMotor.set(0);
    }
    
    public void forkStopLeft(){
    	LeftForkMotor.set(0);
    }
    
    public void forkStopRight(){
    	RightForkMotor.set(0);
    }
    
    public void resetForkEncoder(){
    	LeftForkEncoder.reset();
    	RightForkEncoder.reset();
    }
    
    public boolean atLeftInLimit() {
    	return LeftForkEncoder.getDistance() <= leftForkInnerLimit;
    }
    
    public boolean atRightInLimit() {
    	return RightForkEncoder.getDistance() <= rightForkInnerLimit;
    }
    
    public boolean atLeftOutLimit() {
    	return LeftForkEncoder.getDistance() >= leftForkOuterLimit;
    }
    
    public boolean atRightOutLimit() {
    	return RightForkEncoder.getDistance() >= rightForkOuterLimit;
    }

    public double getDistanceBetweenForks() {
    	return LeftForkEncoder.getDistance() + RightForkEncoder.getDistance() + DistanceBetweenForksOffset;
    }
    
    public void log() {
        SmartDashboard.putBoolean("Left Fork Limit",(atLeftInLimit()||atLeftOutLimit()));
        SmartDashboard.putBoolean("Right Fork Limit",(atRightInLimit()||atRightOutLimit()));
        SmartDashboard.putNumber("Fork Distance", getDistanceBetweenForks());
        SmartDashboard.putNumber("fork left", LeftForkEncoder.getDistance());
        SmartDashboard.putNumber("fork right", RightForkEncoder.getDistance());
    }
    
}

