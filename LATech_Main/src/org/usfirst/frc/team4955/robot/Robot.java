package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public  class Robot extends IterativeRobot {
	
	//----------------------CONSTANTS----------------------
	
	//Drivebase
	public static final int LEFT_DRIVE = 0;
	public static final int RIGHT_DRIVE = 1;
	
	//Two Tower Chain System
	public static final int LEFT_CHAIN = 2;
	public static final int RIGHT_CHAIN = 3;
	
	public static final int LEFT_SWITCH = 0;
	public static final int RIGHT_SWITCH = 1;
	public static final int TOP_SWITCH = 2;
	
	//States Switches
	public boolean isOn = false;
	public boolean isOff = true;
	
	//Buttons and Axes
	public static final int JOYSTICK_PORT_1 = 0;
	public static final int JOYSTICK_PORT_2 = 0;
	public static final int CONTROL = 0;
	
	public static final int RIGHT_JOYSTICK_X = 4;
	public static final int RIGHT_JOYSTICK_Y = 5;
	public static final int LEFT_JOYSTICK_X = 1;
	public static final int LEFT_JOYSTICK_Y = 2;
	
	public static final int A_BUTTON = 1;
	public static final int B_BUTTON = 2;
	public static final int Y_BUTTON= 4;
	public static final int X_BUTTON= 3; 
	
	//Intake System
	public static final int LEFT_INTAKE = 4;
	public static final int RIGHT_INTAKE = 5;
	
	RobotDrive drive;
	Joystick drvStick1, drvStick2, ctlStick;
	Talon rightChain;
	Talon leftChain;
	DigitalInput rightSwitch;
	DigitalInput leftSwitch;
	Encoder encoder;
	IntakeRollers intake;
	TowerChains towers;
	
	int autoLoopCounter;
	
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	drive = new RobotDrive(LEFT_DRIVE,RIGHT_DRIVE);
    	intake = new IntakeRollers(LEFT_INTAKE, RIGHT_INTAKE);
    	towers = new TowerChains(LEFT_CHAIN, RIGHT_CHAIN, LEFT_SWITCH, RIGHT_SWITCH, TOP_SWITCH);
    	drvStick1 = new Joystick(JOYSTICK_PORT_1);
    	drvStick2 = new Joystick(JOYSTICK_PORT_2);
    	ctlStick = new Joystick(CONTROL);
    	leftChain = new Talon(LEFT_CHAIN);
    	rightChain = new Talon(RIGHT_CHAIN);
    	rightSwitch = new DigitalInput(RIGHT_SWITCH);
    	leftSwitch = new DigitalInput(LEFT_SWITCH);
    	encoder = new Encoder(9,8, false, Encoder.EncodingType.k4X);
    	
    	drive.setSafetyEnabled(false);

    	//Invert Left and Right Motors
    	/*drive.setInvertedMotor(MotorType.kRearLeft, true);
    	drive.setInvertedMotor(MotorType.kRearRight, true); */
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			drive.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			drive.drive(0.0, 0.0); 	// stop robot
		}
    }
    

    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	drive.tankDrive(-drvStick1.getY(), -drvStick2.getY(), true);
    	intake.run(ctlStick);
    	towers.run(ctlStick);
    }


    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
