import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class UserFrameWrite extends JFrame implements ActionListener {
	JTabbedPane JTP = new JTabbedPane();
	UserTab1 userTab1 = new UserTab1();
	WritePanel writePanel = new WritePanel();
	Date dateV = new Date(System.currentTimeMillis());
	SimpleDateFormat dateModel = new SimpleDateFormat("yyyy-MM-dd  HH:mm.ss");
	
	public UserFrameWrite() {
		Log log = Log.getInstance();
		String id = log.getId();
		setTitle("WritePost - " + id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 589);
		setLocationRelativeTo(null);
		userTab1.writeButton.addActionListener(this);
		writePanel.completeButton.addActionListener(this);
		writePanel.cancelButton.addActionListener(this);
		
		JTP.add("°Ô½ÃÆÇ", writePanel);
		add(JTP);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == writePanel.completeButton) {
			
			String name = "";
			Log log = Log.getInstance();
			String id = log.getId();
			
			String title = writePanel.titleField.getText();
			String content = writePanel.contentArea.getText();
			String date = dateModel.format(dateV);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
				String sql = "select * from dbperson.personinfo where id = "+"'"+id+"'";
				Statement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(sql);
				
				while(rs.next()){
					name = rs.getString("name");
				}

				Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
				Statement sql1 = DB.createStatement();
				sql1.executeUpdate(String.format("insert into boardContents (id, name, title, content, date) values ('%s', '%s', '%s', '%s', '%s')", id, name, title, content, date));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
			new UserFrame().setVisible(true);
		}
		if(e.getSource() == writePanel.cancelButton) {
			dispose();
			new UserFrame().setVisible(true);
		}
	}
}