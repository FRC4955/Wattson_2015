package org.usfirst.frc.team4955.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;

public class TowerChains {
	
	public static final int LB_BUTTON = 5;
	public static final int RB_BUTTON = 6;
	private Talon leftChain, rightChain;
	private ShittyEncoder rightEncoder, leftEncoder;
	private DigitalInput topSwitch;
	
	TowerChains(int leftChainPWM, int rightChainPWM, int leftSwitchPWM, int rightSwitchPWM,
				int topSwitchIO) {
    	leftChain = new Talon(leftChainPWM);
    	rightChain = new Talon(rightChainPWM);
    	leftEncoder = new ShittyEncoder(leftSwitchPWM);
    	rightEncoder = new ShittyEncoder(rightSwitchPWM);
    	topSwitch = new DigitalInput(topSwitchIO);
	}

	void run (Joystick ctl) {
		if(ctl.getRawButton(LB_BUTTON) && !topSwitch.get()) {
			if(leftEncoder.getCount() == rightEncoder.getCount()){
				System.out.println("Tower Chain Up (LB)");
				leftChain.set(1.0);
				rightChain.set(-1.0);
				leftEncoder.run(true);
				rightEncoder.run(true);
			}else if(leftEncoder.getCount() > rightEncoder.getCount()){
				System.out.println("Right Tower Chain Up (LB)");
				leftChain.set(0);
				rightChain.set(-1.0);
				leftEncoder.run();
				rightEncoder.run(true);
			}else{
				System.out.println("Left Tower Chain Up (LB)");
				leftChain.set(1.0);
				rightChain.set(0);
				leftEncoder.run(true);
				rightEncoder.run();
			}
		} else if(ctl.getRawButton(RB_BUTTON)) {
			if(leftEncoder.getCount() == rightEncoder.getCount()){
				System.out.println("Tower Chain Up (LB)");
				leftChain.set(-1.0);
				rightChain.set(1.0);
				leftEncoder.run(false);
				rightEncoder.run(false);
			}else if(leftEncoder.getCount() < rightEncoder.getCount()){
				System.out.println("Right Tower Chain Up (LB)");
				leftChain.set(0);
				rightChain.set(1.0);
				leftEncoder.run();
				rightEncoder.run(false);
			}else{
				System.out.println("Left Tower Chain Up (LB)");
				leftChain.set(-1.0);
				rightChain.set(0);
				leftEncoder.run(false);
				rightEncoder.run();
			}
		} else {
			leftChain.set(0);
			rightChain.set(0);
			leftEncoder.run();
			rightEncoder.run();
		}
	}
	
	

}
