package macro.controller;

import macro.model.ImagePath;

public class MainController extends Thread {
	
	FindController findCtrl = new FindController();
	
	
	//이미지 파일명을 보내, 해당 파일과 동일한 이미지 탐색 후 좌표 리턴.
	public int[] findXY(String fileName) {
		
		int [][][] coordinate = findCtrl.run(fileName);
		
		int [] xy = findCtrl.findXY(coordinate);
		
		return xy;
	}
	

	@Override
	public void run() {
		
		try {
			
			MouseController mouseCtrl = new MouseController();
			ImagePath path = new ImagePath();
			boolean isStart = false;
			boolean isEnd = false;
			int [] xy = new int[2]; 
			
			System.out.println("5초 후 매크로가 실행됩니다.");
			
			//5초 후 시작.
			Thread.sleep(5000);

			//출격 버튼 찾기.
			System.out.println("출격 버튼 클릭");
			xy = this.findXY(path.getStep2());
			//마우스 이동 -> 65와 타 버튼의 구분을 위해 x좌표 50 수정.
			xy[0] -= 50;
			isStart = mouseCtrl.mouseMove(xy);
			mouseCtrl.click();
			
			//출발하지 못한 경우 -> 빵이 부족해서.
			if(!isStart) {
				System.out.println("출격 실패 - 빵 충전");
				
				Thread.sleep(2000);
				//빵 충전 이미지 탐색
				xy = this.findXY(path.getStep8());
				//마우스 이동
				mouseCtrl.mouseMove(xy);
				//마우스 클릭
				mouseCtrl.click();
				
				
				//마우스를 살짝 내려 한번 더 눌러줘야 함.
				Thread.sleep(3000);
				System.out.println("빵충전 후 클릭");
				xy[1] -= 50; //50만큼 세로축을 내린다.
				//마우스 이동
				mouseCtrl.mouseMove(xy);
				//마우스 클릭
				mouseCtrl.click();
				
				//출격
				Thread.sleep(3000);
				System.out.println("출격 버튼 클릭");
				xy = this.findXY(path.getStep2());
				//마우스 이동 -> 65와 타 버튼의 구분을 위해 x좌표 50 수정.
				xy[0] -= 50;
				isStart = mouseCtrl.mouseMove(xy);
				mouseCtrl.click();
				
			}
			
			Thread.sleep(5000);
			System.out.println("시작버튼 탐색");
			//전투 진입 후 시작 버튼 탐색.
			xy = this.findXY(path.getStep3());
			mouseCtrl.mouseMove(xy);
			mouseCtrl.click();
			
			//오토버튼이 있는 경우 누른다.
			Thread.sleep(5000);
			System.out.println("오토여부 확인");
			xy = this.findXY(path.getStep4());			
			boolean isAuto = mouseCtrl.mouseMove(xy);		
			if(isAuto) {
				System.out.println("오토 클릭");
				mouseCtrl.click();
			}else {
				System.out.println("오토 ok");
			}
			

			//전투가 종료될 때까지 대기.
			while(!isEnd) {
				
				//10초마다 검사.
				Thread.sleep(10000);
				
				//전투정산 화면이 나왔는지 탐색.
				int [] temp = this.findXY(path.getStep5());
				boolean end = mouseCtrl.mouseMove(temp);
				
				//전투정산 화면이 나온 경우 클릭 및 루프 해제.
				if(end) {
					mouseCtrl.click();
					isEnd = true;
					//마우스 재이동.
					mouseCtrl.mouseMove(xy);
					
					System.out.println("전투 종료");
					break;
				}else {
					System.out.println("전투 중");
				}
			} // while
			

			//전투정산 후 -> 보물상자 등장
			Thread.sleep(6000);
			System.out.println("보물상자 등장 - 마우스 이동 불필요");
			//xy = this.findXY(path.getStep6());
			mouseCtrl.mouseMove(xy);
			mouseCtrl.click();
			
			
			//보물상자 클릭 후 -> 화면 이동
			Thread.sleep(5000);
			System.out.println("보물상자 개방 - 마우스 이동 불필요");
			//xy = this.findXY(path.getStep7());
			mouseCtrl.mouseMove(xy);
			mouseCtrl.click();
		
			Thread.sleep(5000);
			System.out.println("종료 되었습니다.");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
