package example2;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame02 extends JFrame{
	// 익명의 inner class 를 이용해서 인터 페이스 type 의 참조값 얻어내기
	ActionListener listener = new ActionListener() {
		
		// 이 ActionListener 객체를 등록한 버튼을 누르면 호출되는 메소드
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("버튼을 눌렀네?");
		}
	};
	
	// 생성자
	public MyFrame02(){
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
		JButton button2 = new JButton("눌러보셈2");
		// 버튼 리스너 객체 등록하기
		button.addActionListener(listener);
		//button2.addActionListener(listener);
		
		// 버튼을 패널에 추가하기
		panel.add(button);
		panel.add(button2);
		// 페널을 프레임의 북쪽에 추가하기
		this.add(panel, BorderLayout.EAST);
		
		setBounds(200, 200, 300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MyFrame02();
	}
}
