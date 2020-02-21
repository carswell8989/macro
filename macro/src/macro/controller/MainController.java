package macro.controller;

public class MainController {
	
	//이미지 탐색 및 클랙
	public void findAndClick(String fileName) {
		
		FindController findCtrl = new FindController();
		ClickController clickCtrl = new ClickController();
		
		//이미지 탐색
		int [][][] coordinate = findCtrl.run(fileName);
		//클릭
		clickCtrl.run(coordinate);
		
	}

}
