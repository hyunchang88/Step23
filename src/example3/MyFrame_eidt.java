package example3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class MyFrame_eidt extends JFrame implements ActionListener{
	
	// 맴버필드 정의하기
	JTextField inputNum, inputTime, inputDoit, inputDone;
	DefaultTableModel model;
	JButton saveBtn, deleteBtn, updateBtn, exportBtn;
	JTable table;
	
	// List 에 Map 을 담아
	List<Map<String, Object>> members;
	
	// 생성자
	public MyFrame_eidt(){
		initUI();
	}
	// 메소드
	public void initUI(){
		// 레이아웃 설정
		setLayout(new BorderLayout());
		// 상단 페널 객체 생성
		JPanel topPanel=new JPanel();
		JPanel bottomPanel=new JPanel();
		
		// 레이블 객체 생성
		JLabel label1=new JLabel("번호");
		JLabel label2=new JLabel("시간");
		JLabel label3=new JLabel("할일");
		JLabel label4=new JLabel("수행여부");
		
		// 텍스트 필드 객체 생성
		inputNum = new HintTextField("  숫자만 입력하세요  	");
		inputTime=new HintTextField("  ex) 1200-2400	 ");
		inputDoit=new HintTextField("  일과 입력           ");
		inputDone=new HintTextField("  O or X	  ");
		
		saveBtn=new JButton("저장");
		deleteBtn=new JButton("삭제");
		updateBtn=new JButton("수정");
		exportBtn=new JButton("엑셀로 저장");
		// 버튼 리스너 등록
		saveBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		exportBtn.addActionListener(this);
		
		// 페널에 컴포넌트 추가 하기 
		topPanel.add(label1);
		topPanel.add(inputNum);
		topPanel.add(label2);
		topPanel.add(inputTime);
		topPanel.add(label3);
		topPanel.add(inputDoit);
		topPanel.add(label4);
		topPanel.add(inputDone);
		topPanel.add(saveBtn);
		topPanel.add(deleteBtn);
		topPanel.add(updateBtn);
		bottomPanel.add(exportBtn);
		
		// 프레임의 상단에 페널 배치하기
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel,  BorderLayout.SOUTH);
		
		// 테이블 칼럼명
		String[] colName={"번호", "시간", "할일", "수행여부"};
		// 테이블에 연결할 모델 객체
		model = new DefaultTableModel(colName, 0);
		
		// 테이블 객체 생성
		table = new JTable();
		// 테이블에 모델 연결
		table.setModel(model);
		
		// 스크롤 할수 있는 UI 로 테이블을 감싼다.
		JScrollPane tablePanel = new JScrollPane(table);
		
		add(tablePanel, BorderLayout.CENTER);
		
		
		// 가로 세로 크기 지정하기
		setSize(1000, 500);
		// 화면의 가운데 배치 되도록
		setLocationRelativeTo(null);
		setVisible(true);
		// 창 닫을때 프로세스 종료 되도록
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 파일에 있는 정보 로딩하기
		loadFromFile();
		// 로딩된 정보 출력하기
		displayMember();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 눌러진 버튼의 참조값을 비교해서 분기한다.
		if(e.getSource() == saveBtn){
			// TextField 에 입력한 내용을 읽어온다.
			int num = Integer.parseInt(inputNum.getText());
			
			String time = inputTime.getText(); 
			String doit = inputDoit.getText();
			String done = inputDone.getText();
			// HashMap 객체에 회원의 정보 담기
			Map<String, Object> map = new HashMap<>(); // Generic 은 key 값이 String, Value 는 int, String 여러개 있으니 Object 사용.
			map.put("num", num);
			map.put("time", time);
			map.put("doit", doit);
			map.put("done", done);
			// ArrayList 객체에 회원 한명의 정보를 담고
			//members = new ArrayList<>(); // 저장 버튼을 누를때마다 새로운 객체를 생성하면 안돼! 파일 어레이 객체를 가지고 있다가 거기서 저장을해야돼 (그래서 HashMap 으로 한것.)
			members.add(map);
			saveTofile();
			// 알림창 띄우기
			JOptionPane.showMessageDialog(this, "저장했습니다.");
			inputNum.setText("");
			inputTime.setText("");
			inputDoit.setText("");
			inputDone.setText("");
		}else if(e.getSource() == deleteBtn){
			// 선택된 table row 의 인덱스를 읽어온다.
			int selectedIndex=table.getSelectedRow();
			if(selectedIndex==-1){ // 선택된  row 가 없다면
				JOptionPane.showMessageDialog(this, "삭제할 row 를 선택하세요");
				return; //메소드 끝내기
			}
			// List 에서 해당 인덱스 삭제
			//members.remove(selectedIndex);
			// 알림창 띄우기
			//JOptionPane.showMessageDialog(this, "삭제 되었습니다.");
			int ans = JOptionPane.showConfirmDialog(this,"삭제하시겠습니까?","삭제확인",JOptionPane.YES_NO_OPTION);
			if(ans==JOptionPane.YES_OPTION){
				members.remove(selectedIndex);
				JOptionPane.showMessageDialog(this, "삭제 되었습니다.");
				 }else{
				  JOptionPane.showMessageDialog(this,"삭제취소");
				 }

			// MyDoitList.dat 파일에도 반영
			saveTofile();
			
			
		}else if(e.getSource() == updateBtn){
			// 수정할 row의 참조값을 읽어옴
			int selectedIndex = table.getSelectedRow();
			// 선택하지 않았으면 selectedIndex에 -1 이 들어감
			if(selectedIndex==-1){
				JOptionPane.showMessageDialog(this, "수정할 row 를 선택하세요");
			}
			// 수정할 이름과 주소를 읽어온다. // table.getValueAt ( row index, column index )
			String time = (String)table.getValueAt(selectedIndex, 1);
			String doit = (String)table.getValueAt(selectedIndex, 2);
			String done = (String)table.getValueAt(selectedIndex, 3);
			// List 의 데이터를 수정한다.
			members.get(selectedIndex).put("time", time);
			members.get(selectedIndex).put("doit", doit); // members.get(selectedIndex) 요기 까진 HashMap // 해당하는 key 값에 가서 수정한걸 넣어
			members.get(selectedIndex).put("done", done);
			saveTofile();
			JOptionPane.showMessageDialog(this, "수정했습니다.");

		
		}else if(e.getSource() == exportBtn){
			// 엑셀파일로 저장하기
			try {
				exportTable(table, new File("C:/myFolder/tabledata.xls"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		// 파일에 있는 정보 로딩하기
		loadFromFile();
		// 로딩된 정보 출력하기
		displayMember();
	}
	
	// 맴버필드의 List 에 담긴 내용을 파일에 저장하는 메소드 
	public void saveTofile(){
		
		// 파일에 출력해서 영구 저장한다.
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("c:/myFolder/MyDoitList.dat");
			// 객체 정보를 출력할수 있는 객체 생성하기
			oos = new ObjectOutputStream(fos);
			// List 객체 출력하기
			oos.writeObject(members);
			oos.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				if(fos!=null)fos.close();
				if(oos!=null)oos.close();
			}catch(Exception e){}
		}
		
	}
	
	// 파일에 저장된 회원 목록을 읽어들이는 메소드
	public void loadFromFile(){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try{
			fis = new FileInputStream("c:/myFolder/MyDoitList.dat");
			ois = new ObjectInputStream(fis);
			// 원래 type 으로 casting 해서 필드에 저장한다.
			members = (List<Map<String, Object>>)ois.readObject();
			// members = (List)ois.readObject(); // 원래 List type 이니 이렇게 써도 가능함.
		}catch(FileNotFoundException fne){
			// 파일을 만든적이 없다면 FileNotFoundException 발생
			members = new ArrayList<>(); // 새로운 배열객체를 생성해서 집어 넣어줘
			System.out.println("MyDoitList.dat 파일이 없음");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null)fis.close();
				if(ois!=null)ois.close();
			}catch(Exception e){}
		}
	}
	// 맴버필드에 있는 회원정보를 출력하는 메소드
	public void displayMember(){
		// table 에 출력된 내용 삭제 되도록
		model.setRowCount(0);		
		
		for(Map<String, Object> tmp:members){
			int num = (int)tmp.get("num");
			String time = (String)tmp.get("time");
			String doit = (String)tmp.get("doit");
			String done = (String)tmp.get("done");
			//System.out.println(time+"/"+name+"/"+done);
			// 회원정보를 Object[] 에 순서대로 담은 다음
			Object[] row = new Object[4];
			row[0]=num;
			row[1]=time;
			row[2]=doit;
			row[3]=done;
			// 테이블에 연결된 모델에 전달한다.
			model.addRow(row);
			
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	// 힌트
	class HintTextField extends JTextField implements FocusListener {

		  private final String hint;
		  private boolean showingHint;
		  

		  public HintTextField(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
		    setForeground(Color.GRAY);
		  }

		  @Override
		  public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      showingHint = false;
		    }
		  }
		  @Override
		  public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      showingHint = true;
		    }
		  }

		  @Override
		  public String getText() {
		    return showingHint ? "" : super.getText();
		  }

		}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	// 엑셀
	public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);
        for(int i=0; i < model.getColumnCount(); i++) {
        	out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
        	for(int j=0; j < model.getColumnCount(); j++) {
        		out.write(model.getValueAt(i,j).toString()+"\t");
        	}
        out.write("\n");
        }
	
	    out.close();
	    System.out.println("write out to: " + file);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////

	
	public static void main(String[] args) {
		new MyFrame_eidt();
	}
}

