package example2;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame01 extends JFrame{
	// 생성자
	public MyFrame01(){
		initUI();
	}
	// UI 초기화 메소드
	public void initUI(){
		// 레이아웃 설정
		setLayout(new BorderLayout());
		// 페널 객체 생성하기
		JPanel panel = new JPanel();
		// 버튼 객체 생성하기
		JButton button = new JButton("눌러보셈");
		// 버튼을 패널에 추가하기
		panel.add(button);
		// 페널을 프레임의 북쪽에 추가하기
		this.add(panel, BorderLayout.NORTH);
		
		setBounds(200, 200, 300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MyFrame01();
	}
}
