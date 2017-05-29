package example2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * 		버튼을 눌렀을때 JTextField 에 입력한 내용을 읽어와서 콘솔창에 출력해 보세요
 */
public class MyFrame05 extends JFrame implements ActionListener{
	
   JTextField inputText, resultText;
   JButton sendBtn, clearBtn;
	
   //생성자
   public MyFrame05(){
      initUI();
   }
   //ui초기화 메소드
   public void initUI() {
      //레이아웃 설정
      setLayout(new BorderLayout());
      
      JPanel topPanel = new JPanel();
      JPanel bottomPanel = new JPanel();
      
      inputText = new JTextField(10);
      resultText = new JTextField(10);
      // 버튼의 참조값을 맴버필드에 저장
      sendBtn = new JButton("전송");
      clearBtn = new JButton("Clear");
      
      // 버튼의 리스너 등록하기
      sendBtn.addActionListener(this);
      clearBtn.addActionListener(this);
      // 상단에 위치 시키기
      topPanel.add(inputText);
      topPanel.add(sendBtn);
      topPanel.add(clearBtn);
      
      bottomPanel.add(resultText);
      
      add(topPanel, BorderLayout.NORTH);
      add(bottomPanel, BorderLayout.SOUTH);
   
      setBounds(200,200,300,300);
      setTitle("이것은 프레임이다.");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   public static void main(String[] args) {
      new MyFrame05();
   }
   
	@Override
	public void actionPerformed(ActionEvent e) {
		// 1. JTextField 에 입력한 문자열 읽어오기
		String msg = inputText.getText();
//		// 2. 콘솔에 출력하기
//		//System.out.println(msg);
//		// 3. JTextField 에 문자열 출력하기
//		resultText.setText(msg);
//		// 4. 입력한 내용 지우기
//		inputText.setText("");
		
		// 눌러진 버튼의 참조값을 얻어본다.
		Object obj = e.getSource();
		if(obj == this.sendBtn){ // 전송 버튼을 눌렀을때
			resultText.setText(msg);
			inputText.setText("");
		}else if(obj == clearBtn){ // 클리어 버튼을 눌렀을때
			resultText.setText("");
			inputText.setText("");
		}
		
	}
}

















