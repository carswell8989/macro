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
	
	//이미지의 x y 좌표 구하기.
	public int[] findXY(int [][][] coordinate) {
		
		int [] result = new int[2];

		 //x좌표 -> 맨 마지막 좌표
		 result[0] = coordinate[coordinate.length-1][coordinate[0].length-1][coordinate[0][0].length -1];
		 //y좌표
		 result[1] = coordinate[0][0][0];
		 System.out.println("타겟 이미지 서칭 좌표 : "+result[0]+", "+result[1]);
		
		
		return result;
		
	}
	

}
