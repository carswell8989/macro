package macro;

import java.util.Scanner;

import macro.controller.MainController;

public class Main {
	
	public static int cycle = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		System.out.println("======================");
		System.out.println("랑그릿사 형귀 자동 프로그램");
		System.out.println("본 매크로는 매우 허접합니다.");
		System.out.println("잘 안되면 문의해주세요.");
		System.out.println("시작방법 : ");
		System.out.println("1. 비경 입장");
		System.out.println("2. 형귀 헬스장 클릭");
		System.out.println("3. 왼쪽에서 가고자하는 헬스장 클릭");
		System.out.println("열심히 만들었는데 잘 될지 모르겠습니다.");
		System.out.println("Made by 히트야. 청룡기사단 만세!");
		System.out.println("======================");

		System.out.print("바퀴 수를 지정해주세요(숫자만 입력, 입력 후 엔터) : ");
		
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		System.out.println("");	
		System.out.println("입력한 숫자는 "+num+" 입니다. 그럼 시작하겠습니다.");	
		MainController mainController = new MainController();
		
		cycle = num;
		
		Thread t1 = new Thread(mainController);
		
		t1.start();
		
	}

}
