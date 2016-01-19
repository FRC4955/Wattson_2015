package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class IntakeRollers {

	public static final int LB_BUTTON = 5;
	public static final int RB_BUTTON = 6;
	private Talon left, right;
	
	IntakeRollers(int leftPWM, int rightPWM) {
    	left = new Talon(leftPWM);
    	right = new Talon(rightPWM);
	}
	
	void run(Joystick ctl){
        if(ctl.getRawButton(LB_BUTTON)) {
        	System.out.println("Intake out (LB)");
        	left.set(1.0);
        	right.set(-1.0);

        } else if(ctl.getRawButton(RB_BUTTON)) {
        	System.out.println("Intake in (RB)");
        	left.set(-1.0);
        	right.set(1.0);

        } else {
        	left.set(0);
        	right.set(0);

        } 
	}
}
