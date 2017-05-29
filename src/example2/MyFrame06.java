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
	
	JTextField inputText;
	JButton sendBtn;
	JTextArea textArea;
	
	
	// 생성자
	public MyFrame06(){
		initUI();
	}
	// UI 초기화 메소드
	public void initUI(){
		// 레이아웃 설정
		setLayout(new BorderLayout());
		// 페널 객체 생성하기
		JPanel panel = new JPanel();
		// 텍스트 상자 및 버튼 객체 생성
		inputText = new JTextField(10);
		sendBtn = new JButton("전송");
		
		// 전송 버튼을 누르면 동작하라고 리스너 등록
		sendBtn.addActionListener(this);
		
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
	public void actionPerformed(ActionEvent arg0) {
		// 입력한 문자열 읽어오기
		String msg = inputText.getText();
		// 눌러진 버튼의 참조값 얻어오기
		Object obj = arg0.getSource();
		
//		while(true){
//			if(obj == this.sendBtn){ // 전송 버튼을 눌렀을때
//				textArea.setText(msg+"\r\n"); // 밑에 칸에 입력한 msg 출력 + 개행
//				inputText.setText("");
//			}else if(msg.equals("q"))break;
//		}
		
		if(obj == this.sendBtn){ // 전송 버튼을 눌렀을때
			textArea.setText(msg+"\r\n"); // 밑에 칸에 입력한 msg 출력 + 개행
			inputText.setText("");
		}
		// 다음줄에 계속 나오게 하려고 while 돌렸는데 무한루프 돈듯 이상해짐
	}
}

