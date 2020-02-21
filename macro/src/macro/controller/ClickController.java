package macro.controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class ClickController {
	
	public void run(int [][][] coordinate) {
		
		try {
			Robot robot = new Robot();
			
			 robot.mouseMove(coordinate[0][0][0]+15, coordinate[0][0][1]);
			 robot.delay(50);
			 robot.mousePress(InputEvent.BUTTON1_MASK);
			 robot.delay(50);
			 robot.mouseRelease(InputEvent.BUTTON1_MASK);
			 
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
