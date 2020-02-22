package macro.util;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSearch {

	 BufferedImage screenShotImage;    //화면 캡처
     BufferedImage keyImage;           //검색 대상 그림
     Image imagekey;
 
     int scrShotImgWidth;              //스크린샷 너비
     int scrShotImgHeight;             //스크린샷 높이
   
     int keyImgWidth;                  //검색 대상 이미지 너비
     int keyImgHeight;                 //검색 대상 이미지 높이
      
     int[][] screenShotImageRGBData;   //화면 캡처 RGB 데이터
     int[][] keyImageRGBData;          //검색 대상 그림 RGB 데이터
      
     int[][][] findImgData;            //검색 결과 목표 아이콘 화면 캡처 위의 좌표 데이터 
      
      
      
      public int[][][] run() {
    	  
    	  if(null == this.keyImage) {
    		  return new int[0][0][0];
    	  }
    	  
    	  //변수 셋팅.
    	  screenShotImage = this.getFullScreenShot();
    	  screenShotImageRGBData = this.getImageGRB(screenShotImage);
          keyImageRGBData = this.getImageGRB(keyImage);
          scrShotImgWidth = screenShotImage.getWidth();
          scrShotImgHeight = screenShotImage.getHeight();
          keyImgWidth = keyImage.getWidth();
          keyImgHeight = keyImage.getHeight();
          
          //이미지 검색 시작
          boolean result = this.findImage();
    	  
          
          return this.findImgData;
          
          
         

      }
      
      
      /**
       * 타겟 이미지 셋팅
       */
      
      public void setTargetImage(String fileName) {
    	  
    	  System.out.println("file path >>> "+fileName);
    	  
    	  try {
			keyImage = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
      }
      
      
      /**
       * 전체 화면 캡처
       * @return 복귀 BufferedImage
       */
      public BufferedImage getFullScreenShot() {
          BufferedImage bfImage = null;
          int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
          int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
          try {
              Robot robot = new Robot();
              bfImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
          } catch (AWTException e) {
              e.printStackTrace();
          }
          return bfImage;
     }
      
      
      /**
       * 로컬 파일 읽기
       * @param keyImagePath - 그림 절대 경로
       * @return 로컬 그림 BufferedImage 대상
       */
      public BufferedImage getBfImageFromPath(String keyImagePath) {
    	  System.out.println("keyImagePath : "+keyImagePath);
          BufferedImage bfImage = null;
          try {
              bfImage = ImageIO.read(new File(keyImagePath));
          } catch (IOException e) {
              e.printStackTrace();
          }
          return bfImage;
      }
	
      
      /**
       * BufferedImage 그림 RGB 배열 가져오기
       * @param bfImage
       * @return
       */
      public int[][] getImageGRB(BufferedImage bfImage) {
          int width = bfImage.getWidth();
          int height = bfImage.getHeight();
          int[][] result = new int[height][width];
          for (int h = 0; h <height; h++) {
              for (int w = 0; w <width; w++) {
                  //사용 getRGB (w, h) 가져오는 중 이 색을 값, ARGB 아닌 실제 프로그램 중 사용, RGB. 그래서 다변화가 필요하다 ARGB 전환의 만든 RGB, 즉 bufImg.getRGB(w, h) & 0xFFFFFF. 
                  result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
              }
         }
          return result;
      }
      
      
      /**
       * 동일 이미지 탐색
       */
      public boolean findImage() {
    	  
    	  boolean isFinded = false;
    	  
          findImgData = new int[keyImgHeight][keyImgWidth][2];
          //화면 캡처 픽셀 좀 데이터 사이를 옮겨다니기
          for(int y=0; y<scrShotImgHeight-keyImgHeight; y++) {
              for(int x=0; x<scrShotImgWidth-keyImgWidth; x++) {
                  //대상 그림 크기 근거하여 얻은 목표 도 사각 맵 화면 위에 네 시 까지, 
                  //판단 스크린샷 위에 대응 네 개 좀 와 그림 B 네 개 픽셀 값을 같은 있는지 좀, 
                  //만약 같은 스며들게 화면 위에 맵 범위 내의 모든 좀 목표 도 모든 좀 비교해 보다. 
                  if((keyImageRGBData[0][0]^screenShotImageRGBData[y][x])==0
                          && (keyImageRGBData[0][keyImgWidth-1]^screenShotImageRGBData[y][x+keyImgWidth-1])==0
                          && (keyImageRGBData[keyImgHeight-1][keyImgWidth-1]^screenShotImageRGBData[y+keyImgHeight-1][x+keyImgWidth-1])==0
                          && (keyImageRGBData[keyImgHeight-1][0]^screenShotImageRGBData[y+keyImgHeight-1][x])==0) {
                      
                      isFinded = isMatchAll(y, x);
                      //만약 비교적 결국 완전히 같지 않으면 설명이 그림 찾을 때까지 충전 중 위치 까지 좌표 데이터 검색 결과 배열. 
                      if(isFinded) {
                          for(int h=0; h<keyImgHeight; h++) {
                              for(int w=0; w<keyImgWidth; w++) {
                                  findImgData[h][w][0] = y+h; 
                                  findImgData[h][w][1] = x+w;
                              }
                          }
                          return isFinded;
                      } // if
                  } // if
              } // for
          } // for
          
          return isFinded;
      }
      
      
      /**
       * 이미지 매칭
       * @param y - 목표 그림 왼쪽 위 픽셀 좀 하고 일치하는 화면 y 좌표
       * @param x - 목표 그림 왼쪽 위 픽셀 좀 하고 일치하는 화면 x 좌표
       * @return 
       */
      public boolean isMatchAll(int y, int x) {
          int biggerY = 0;
          int biggerX = 0;
          int xor = 0;
          for(int smallerY=0; smallerY<keyImgHeight; smallerY++) {
              biggerY = y+smallerY;
              for(int smallerX=0; smallerX<keyImgWidth; smallerX++) {
                  biggerX = x+smallerX;
                  if(biggerY>=scrShotImgHeight || biggerX>=scrShotImgWidth) {
                      return false;
                  }
                  xor = keyImageRGBData[smallerY][smallerX]^screenShotImageRGBData[biggerY][biggerX];
                  if(xor!=0) {
                      return false;
                  }
              }
              biggerX = x;
          }
          return true;
      }
      
	
}
