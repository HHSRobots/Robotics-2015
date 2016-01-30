
package org.usfirst.frc.team554.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team554.robot.commands.AutonomousProgram000;
import org.usfirst.frc.team554.robot.commands.AutonomousProgram002;
import org.usfirst.frc.team554.robot.subsystems.BackArm;
import org.usfirst.frc.team554.robot.subsystems.DriveTrain;
import org.usfirst.frc.team554.robot.subsystems.Camera;
import org.usfirst.frc.team554.robot.subsystems.Fork;
import org.usfirst.frc.team554.robot.subsystems.Elevator;
import org.usfirst.frc.team554.robot.subsystems.PDP;
import org.usfirst.frc.team554.robot.subsystems.ThumbWheel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	
	public static DriveTrain drivetrain;
	public static Camera camera;
	public static Fork fork;
	public static Elevator elevator;
	public static BackArm backarm;
	public static ThumbWheel twheel;
	public static OI oi;
	public static PDP PowerDistPanel;
	public int AutoProgramNumber;
	public int CameraUpdate;
    
	//public static SendableChooser AutoChooser; 

    Command autonomousCommand;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		
		drivetrain = new DriveTrain();
		camera = new Camera();
		fork = new Fork();
		elevator = new Elevator();
		backarm = new BackArm();
		PowerDistPanel = new PDP();
		twheel = new ThumbWheel();
		oi = new OI();
		//AutoChooser = new SendableChooser();
		//AutoChooser.addDefault("DoNothing",new AutonomousProgramnull());
		//AutoChooser.addObject("MoveContainer", new AutonomousProgram000());
		//SmartDashboard.putData("AutoProgam", AutoChooser);
		
		fork.resetForkEncoder();
		drivetrain.reset();
		
		camera.cameraFront();
		// Show what command your subsystem is running on the SmartDashboard
        // SmartDashboard.putData(drivetrain);
        // SmartDashboard.putData(elevator);
        SmartDashboard.putData(fork);
        // SmartDashboard.putData(backarm);
        
        CameraUpdate = 1;
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
		AutoProgramNumber = twheel.getThumbWheelval();
		switch (AutoProgramNumber) {
			case 1: autonomousCommand =	new AutonomousProgram000();
				break;
			case 2: autonomousCommand = new AutonomousProgram002();
		        break;
		default: ;//do nothing
		};
    	//autonomousCommand = (Command) AutoChooser.getSelected();
    	if (autonomousCommand != null) autonomousCommand.start();
		
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        //while (isEnabled()){
        	
       // }
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        	Scheduler.getInstance().run();
        	
        if (CameraUpdate==1){
        	camera.updateCam();
        }
        CameraUpdate = -1* CameraUpdate;
        
		// SmartDashboard.putNumber("pov", oi.getJoystickOperator().getPOV(0));
        log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void log() {
    	fork.log();
    	elevator.log();
    	backarm.log();
    	drivetrain.log();
    	twheel.log();
    	PowerDistPanel.log();
    }
}
