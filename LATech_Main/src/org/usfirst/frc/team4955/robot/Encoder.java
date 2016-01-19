package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.DigitalInput;

public class ShittyEncoder {
	
	DigitalInput digitalSwitch;
	
	boolean lastState, lastUp;
	
	int count;
	
	ShittyEncoder(int encoderIO){
		digitalSwitch = new DigitalInput(encoderIO);
		lastState = digitalSwitch.get();
		count = 0;
	}
	
	// Call this a bunch
	void run(boolean goingUp){
		boolean state = digitalSwitch.get();
		if(lastState != state){
			if(goingUp){
				count ++;
			}else{ // going down
				count --;
			}
			lastState = state;
		}
		lastUp = goingUp;
	}
	
	void run(){
		run(!lastUp);
	}
	
	int getCount(){
		return count;
	}
	
	void reset(){
		count = 0;
	}
}
