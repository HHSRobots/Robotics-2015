package org.usfirst.frc.team554.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team554.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Joystick joystick_driver = new Joystick(0);
	private Joystick joystick_operator = new Joystick(1);
	
	public OI(){
		// Create buttons

		JoystickButton L1 = new JoystickButton(joystick_driver,5);
		JoystickButton R1 = new JoystickButton(joystick_driver,6);		
		
		// Connect the buttons to commands
		
		L1.whenPressed(new CameraFront());
		R1.whenPressed(new CameraSide());
		
		JoystickButton LeftForkOutButton = new JoystickButton(joystick_operator,1);
		JoystickButton RightForkOutButton = new JoystickButton(joystick_operator,2);
		JoystickButton RightForkInButton = new JoystickButton(joystick_operator,3);
		JoystickButton LeftForkInButton = new JoystickButton(joystick_operator,4);
		
		JoystickButton ShiftLeftButton = new JoystickButton(joystick_operator,5);
		JoystickButton ShiftRightButton = new JoystickButton(joystick_operator,6);
		JoystickButton CloseForksButton = new JoystickButton(joystick_operator,7);
		JoystickButton OpenForksButton = new JoystickButton(joystick_operator,8);
		JoystickButton CloseForksToDistanceButton = new JoystickButton(joystick_operator,9);
		JoystickButton OpenForksToDistanceButton = new JoystickButton(joystick_operator,10);
		
		LeftForkOutButton.whenPressed(new MoveLeftForkOut());
		LeftForkInButton.whenPressed(new MoveLeftForkIn());
		
		RightForkOutButton.whenPressed(new MoveRightForkOut());
		RightForkInButton.whenPressed(new MoveRightForkIn());
		
		ShiftLeftButton.whenPressed(new ShiftLeft());
		ShiftRightButton.whenPressed(new ShiftRight());
		
		CloseForksButton.whenPressed(new CloseFork());
		OpenForksButton.whenPressed(new OpenFork());
		
		CloseForksToDistanceButton.whenPressed(new CloseForksToDistance(16.05));
		OpenForksToDistanceButton.whenPressed(new OpenForksToDistance(21.95));
}
	
	public Joystick getJoystickDriver(){
		return joystick_driver;
	}
	
	public Joystick getJoystickOperator(){
		return joystick_operator;
	}
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

