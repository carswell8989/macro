package macro.controller;

import macro.util.ImageSearch;

public class FindController {
	
	//ImageSearch 를 활용하여 이미지를 찾는다.
	public int[][][] run(String fileName) {
		
		ImageSearch is = new ImageSearch();
		
		is.setTargetImage(fileName);
		
		int [][][] result = is.run();

		return result;
	}
	
	
	

}
