package macro.model;

public class ImagePath {
	
	/**
	 * 시나리오를 작성한다.
	 * step1,2,3...
	 * 해당 시나리오 대로 MainController에게 이미지를 제공한다.
	 * 
	 */

	String path = this.getClass().getResource("/").getPath()+"image/";
	
	String step1 = this.path+"step1.png"; // 형귀 -> 선택하게 할거임
	
	String step2 = this.path+"step2.png"; //출발 버튼
	
	String step3 = this.path+"step3.png"; //시작 버튼
	
	String step4 = this.path+"step4.png"; // 오토
	
	String step5 = this.path+"step5.png"; // 전투정산
	
	String step6 = this.path+"step6.png"; // 종료1
	
	String step7 = this.path+"step7.png"; // 종료2
	
	String step8 = this.path+"step8.png"; // 빵부족

	String step9 = this.path+"step9.png"; // 빵 먹고 나서.
	
	String step11 = this.path+"step11.png"; //하단 안내 메시지
	
	public String getStep1() {
		return step1;
	}

	public void setStep1(String step1) {
		this.step1 = step1;
	}

	public String getStep2() {
		return step2;
	}

	public void setStep2(String step2) {
		this.step2 = step2;
	}

	public String getStep3() {
		return step3;
	}

	public void setStep3(String step3) {
		this.step3 = step3;
	}

	public String getStep4() {
		return step4;
	}

	public void setStep4(String step4) {
		this.step4 = step4;
	}

	public String getStep5() {
		return step5;
	}

	public void setStep5(String step5) {
		this.step5 = step5;
	}

	public String getStep6() {
		return step6;
	}

	public void setStep6(String step6) {
		this.step6 = step6;
	}

	public String getStep7() {
		return step7;
	}

	public void setStep7(String step7) {
		this.step7 = step7;
	}

	public String getStep8() {
		return step8;
	}

	public void setStep8(String step8) {
		this.step8 = step8;
	}

	public String getStep9() {
		return step9;
	}

	public void setStep9(String step9) {
		this.step9 = step9;
	}

	public String getStep11() {
		return step11;
	}

	public void setStep11(String step11) {
		this.step11 = step11;
	}
	
	
	
}
