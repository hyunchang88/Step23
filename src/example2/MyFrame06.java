package example2;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame06 extends JFrame implements ActionListener{
	
	// 맴버필드
//	ActionListener listener = new ActionListener(){ // listener 는 클래스의 맴버 필드로 액션리스너 타입을 가지고 있는 것.
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			
//		}};
		
	JTextField inputText;
	JButton sendBtn;
	JTextArea textArea;													// 파랑색은 Field
	
	
	// 생성자
	public MyFrame06(){
		initUI();
	}
	// UI 초기화 메소드
	public void initUI(){
		// 레이아웃 설정
		setLayout(new BorderLayout());
		// 페널 객체 생성하기
		JPanel panel = new JPanel();									// 갈색은 Local value
		// 텍스트 상자 및 버튼 객체 생성
		inputText = new JTextField(10);
		sendBtn = new JButton("전송");
		
		// 전송 버튼을 누르면 동작하라고 리스너 등록
		sendBtn.addActionListener(this); // this 위치가 액션리스너 타입의 참조값 (implements ActionListener 해줘서 프레임 자체가 액션리스너 타입) // 이러면 실행 순서는 public void actionPerformed(ActionEvent arg0) { } 여기로 들어감.
		//sendBtn.addActionListener(listener); // 맴버 필드에 ActionListener listener = new ActionListener(){ } 이거 해주면 이렇게 코딩도 가능함. // 이러면 실행 순서는  new ActionListener(){ } 안으로 들어가 
		
		panel.add(inputText);
		panel.add(sendBtn);
		// new JTextArea ( row , column)
		textArea = new JTextArea(10, 10);
		add(panel, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		
		//textArea.setText("Hello !!!!");
//		textArea.append("one\r\n");
//		textArea.append("two\r\n");
//		textArea.append("three\r\n");
		
		setBounds(200, 200, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MyFrame06();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) { // Action Listener 인터페이스에 정의된 추상 메소드
		// 입력한 문자열 읽어오기
		String msg = inputText.getText();
		// 눌러진 버튼의 참조값 얻어오기
		Object obj = arg0.getSource();
		// JTextArea 에 추가하기
		textArea.append(msg+"\r\n"); // 밑에 칸에 입력한 msg 출력 + 개행
		// 입력창 지우기
		inputText.setText("");

	}
}

