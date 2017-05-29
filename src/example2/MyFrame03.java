package example2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame03 extends JFrame implements ActionListener{//MyFrame02을 ActionListener 타입으로 만들어 버린것

   
   //생성자
   public MyFrame03(){
      initUI();
   }
   //ui초기화 메소드
   public void initUI() {
      //레이아웃 설정
      setLayout(new BorderLayout());
      //패널객체 생성하기
      JPanel panel=new JPanel();
      //버튼객체 생성하기
      JButton button=new JButton("누질러보셈");
      JButton button2=new JButton("누질러보셈2");
      
      button.addActionListener(this);//addActionListener 인터페이스 타입
      //this - MyFrame02 가 ActionListener 가 되었기 때문
//	  button2.addActionListener(l);
      
      //버튼을 패널에 추가하기
      panel.add(button);
      panel.add(button2);
      //패널을 프레임의 북쪽에 추가하기
      this.add(panel, BorderLayout.SOUTH);
      
      setBounds(200,200,300,300);
      setTitle("이것은 프레임이다.");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
   public static void main(String[] args) {
      new MyFrame03();
   }
   //ActionListener 인터페이스 때문에 강제 구현된 메소드
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      System.out.println("버튼을 눌렀네요");
      
   }
}