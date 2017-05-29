package example1;

import javax.swing.JFrame;

public class HelloFrame extends JFrame{
	// 생성자
	public HelloFrame(){
		// setBounds(x, y, width, height)
		this.setBounds(100, 100, 200, 200);
		// close 버튼을 클릭했을때 프로세스가 종료 되도록 설정
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 화면에 보이도록 설정
		this.setVisible(true); // 나의 맴버 메소드라 this 생략가능
	}
	// 메인 스레드가 시작되는 메인 메소드
	public static void main(String[] args) {
		// HelloFrame 클래스를 이용해서 객체 생성하기
		new HelloFrame();
	}
}
