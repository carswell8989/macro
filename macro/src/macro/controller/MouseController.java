package macro.controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseController {
	
	Robot robot;
	
	//생성자에서 로봇 생성.
	public MouseController() throws AWTException {
		robot = new Robot();
	}
	
	//마우스를 클릭하는 이벤트
	public void click() throws AWTException {

		 robot.mousePress(InputEvent.BUTTON1_MASK);
		 robot.delay(50);
		 robot.mousePress(InputEvent.BUTTON1_MASK);
		 robot.delay(50);
		 robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
	}
	
	//마우스를 움직이는 이벤트.
	public boolean mouseMove(int [] coordinate) throws AWTException {
		
		//x좌표가 0보다 같거나 작으면 -> 이동하지않고 false 리턴.
		 if(coordinate[0] <= 0) {
			 return false;
		 }

		 
		 robot.mouseMove(coordinate[0], coordinate[1]);
		 System.out.println("마우스 이동 좌표 : "+coordinate[0]+", "+coordinate[1]);
		 
		 return true;
	}
	
	

	

}
