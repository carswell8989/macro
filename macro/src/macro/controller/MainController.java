package macro.controller;

import macro.Main;
import macro.model.ImagePath;

public class MainController extends Thread {
	
	FindController findCtrl = new FindController();
	
	ImagePath path = new ImagePath();
	
	//이미지 파일명을 보내, 해당 파일과 동일한 이미지 탐색 후 좌표 리턴.
	public int[] findXY(String fileName) {
		
		int [][][] coordinate = findCtrl.run(fileName);
		
		int [] xy = findCtrl.findXY(coordinate);
		
		return xy;
	}
	

	@Override
	public void run() {
		
		System.out.println("5초 후 매크로가 실행됩니다.");
		int count = 0;
		
		//최초 입력한 사이클만큼 반복하도록 한다.
		while(Main.cycle > count) {

		try {
			
			MouseController mouseCtrl = new MouseController();
			
			boolean isStart = false;
			
			
			//5초 후 시작.
			System.out.println("현재 바퀴 수 : "+count);
			
			//출격 버튼 찾기.
			isStart = this.intoBattle(mouseCtrl);
			
			//출발하지 못한 경우 -> 빵이 부족한 것인지 확인.
			if(!isStart) {
				
				boolean isCharge = this.charge(mouseCtrl);
				
				//충전 성공.
				if(isCharge) {
					//재시작.
					isStart = this.intoBattle(mouseCtrl);
				}else {
					System.out.println("알 수 없는 문제 발생. 처음부터 재시작 합니다.");
					isStart = this.intoBattle(mouseCtrl);
				}
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			count++;
		} // while
		
		
		
		
	}
	
	
	 //출격 -> 시작을 호출한다.
	 public boolean intoBattle(MouseController mouseCtrl) throws Exception {
		 
		 //5초대기
		 Thread.sleep(5000);
		 
		 int [] xy = new int[2];
		 
		 boolean isStart = false;
		 xy = this.findXY(path.getStep2());
		 //마우스 이동 -> 65와 타 버튼의 구분을 위해 x좌표 50 수정.
		 xy[0] -= 50;
		 isStart = mouseCtrl.mouseMove(xy);
		 
		 if(isStart) {
			 System.out.println("출격 버튼 클릭");
			 mouseCtrl.click();
			 return this.startBattle(mouseCtrl);
			 
		 }
		 
		 return isStart;
		 
	 }
	 
	 //시작 -> 종료를 호출한다.
	 public boolean startBattle(MouseController mouseCtrl) throws Exception {

		 int count = 1;
		 
		 //5초대기
		 Thread.sleep(5000);
		
		 int [] xy = new int[2];
		 
		 boolean isStart = false;
		 boolean isEnd = false;
		 
		 xy = this.findXY(path.getStep3());
		 
		 isStart = mouseCtrl.mouseMove(xy);
		 
		 if(isStart) {
			 System.out.println("시작 버튼 클릭");
			 mouseCtrl.click();
			 //오토버튼 클릭 호출. -> 실패시 이미 오토이므로 상관없음.
			 this.clickAuto(mouseCtrl, path);
			 
			 //전투 종료 확인.
			 while(!isEnd) {
				 
				 	//10분내로 전투가 종료되지 않는 경우 문제가 있다고 판단.
				 	if(count > 60) {
				 		 System.out.println("10분째 전투가 종료되지 않았습니다.");
				 		 return false;
				 	}

				 	//10초마다 탐색
				   Thread.sleep(10000);
				   System.out.println("전투 종료 여부 확인");
				  
				   //전투정산 화면이 나왔는지 확인.
				   int [] temp = new int[2];
				   temp = this.findXY(path.getStep5());
				   isEnd =  mouseCtrl.mouseMove(temp);
				   
				   if(isEnd) {
					   System.out.println("전투 종료.");
					   //마우스 제자리로
					   mouseCtrl.mouseMove(xy);
					   //보상 오픈 호출
					   this.endBattle(mouseCtrl, xy);
				   }else {
					   System.out.println("전투 중. 10초뒤 다시 확인합니다.");
					   System.out.println("현재 전투 진행 시간 : "+count*10+"초");
					   count++; 
				   } 
			 } // while
			 
			 
		 }
		 
		 return isStart;
		 
	 }
	 
	 //오토버튼
	 public void clickAuto(MouseController mouseCtrl, ImagePath path) throws Exception {
		 
		 //5초대기
		 Thread.sleep(5000);
		 
		 int [] xy = new int[2];

		 xy = this.findXY(path.getStep4());
		 
		 boolean isAuto = mouseCtrl.mouseMove(xy);		
			if(isAuto) {
				System.out.println("오토 클릭");
				mouseCtrl.click();
			}else {
				System.out.println("오토 ok");
			}
		 
	 }
	 
     
	 //전투 종료 여부 확인 및 클릭. -> 보상 상자 개방을 호출한다.
	 public boolean endBattle(MouseController mouseCtrl, int [] xy) throws Exception {
		
		 boolean isEnd = false;
		 
		 int [] temp = new int[2];
		 
		 temp = this.findXY(path.getStep5());
		 
		 
		 //전투정산 팝업.
		 isEnd = mouseCtrl.mouseMove(temp);
		 
		 if(isEnd) {
			 System.out.println("전투정산 화면입니다.");
			 //마우스를 다시 제자리로 옮긴다.
			 mouseCtrl.mouseMove(xy);
			 mouseCtrl.click();			 
			 //보물상자 개방을 호출한다.
			 return this.openBox(mouseCtrl, xy);
		 }
		 
		 return isEnd;
		 
	 }
	 
	 
	 //보상 상자 개방
	 public boolean openBox(MouseController mouseCtrl, int [] xy) throws Exception {
		 
		 //5초대기
		 Thread.sleep(5000);
		 
		 boolean isOpen = true;
		 
		 //종료될 때까지 버튼 눌러야함.
		 while(isOpen) {
			 Thread.sleep(4000);
			 System.out.println("보상을 받고 있습니다.");
			 //파티버튼이 눌러질 위험이 있어 왼쪽으로 이동시킨다.
			 xy[0] -= 100;
			 mouseCtrl.mouseMove(xy);
			 mouseCtrl.click();
			 //출격버튼이 있는지 확인. -> 출격버튼 이 있다면 종료.
			 int [] temp = this.findXY(path.getStep2());
			 boolean isEnd = mouseCtrl.mouseMove(temp);
	
			 if(isEnd) {
				 break;
			 }			 
		 }

		 
		 return isOpen;
	 }
	 
	 //스태미너 충전 - 형귀버거만 사용.
	 public boolean charge(MouseController mouseCtrl) throws Exception {
		 
		 boolean isCharge = false;
		 int [] xy = new int[2];
		 
		 xy = this.findXY(path.getStep8());
		 //마우스 이동
		 isCharge = mouseCtrl.mouseMove(xy);
		 
		 if(isCharge) {
			 mouseCtrl.click();
			 //3초 대기
			 Thread.sleep(3000);
			 //y좌표를 내린다.
			 xy[1] += 100;
			 mouseCtrl.mouseMove(xy);
			 mouseCtrl.click();			 			 
		 }
		 
		 
		 return isCharge;
	 }

}
