package macro;

import macro.controller.MainController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MainController mainController = new MainController();
		
		Thread t1 = new Thread(mainController);
		
		t1.start();
		
	}

}
