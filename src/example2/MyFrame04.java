package example2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame04 extends JFrame implements ActionListener{
	// 버튼의 참조값을 담을 맴버필드
	JButton saveBtn, deleteBtn;
   
   //생성자
   public MyFrame04(){
      initUI();
   }
   //ui초기화 메소드
   public void initUI() {
      //레이아웃 설정
      setLayout(new BorderLayout());
      //패널객체 생성하기
      JPanel panel=new JPanel();
      //버튼객체 생성하기
      this.saveBtn=new JButton("저장"); // 버튼의 참조값을 필드에 담아
      deleteBtn=new JButton("삭제"); // this 생략 가능
      
      saveBtn.addActionListener(this);//addActionListener 인터페이스 타입
      deleteBtn.addActionListener(this);
      
      //버튼을 패널에 추가하기
      panel.add(saveBtn);
      panel.add(deleteBtn);
      //패널을 프레임의 북쪽에 추가하기
      this.add(panel, BorderLayout.NORTH);
      
      setBounds(200,200,300,300);
      setTitle("이것은 프레임이다.");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   public static void main(String[] args) {
      new MyFrame04();
   }
   //ActionListener 인터페이스 때문에 강제 구현된 메소드
   @Override
   public void actionPerformed(ActionEvent e) {
	   // 눌러진 버튼의 참조값을 얻어본다.
	   Object obj = e.getSource();
	   if(obj == this.saveBtn){ // 저장 버튼을 눌렀을때
		   System.out.println("저장 합니다.");
	   }else if(obj == deleteBtn){ // 삭제 버튼을 눌렀을때
		   System.out.println("삭제 합니다.");
	   }
   }
}