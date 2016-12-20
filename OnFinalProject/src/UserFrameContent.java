import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class UserFrameContent extends JFrame implements ActionListener {
	JTabbedPane JTP = new JTabbedPane();
	UserTab1 userTab1 = new UserTab1();
	ContentPanel contentPanel;
	
	String tempPK = null;
	
	public UserFrameContent(String tempPK) {
		this.tempPK = tempPK;
		contentPanel = new ContentPanel(tempPK);
		
		Log log = Log.getInstance();
		String id = log.getId();
		setTitle("User - " + id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 589);
		setLocationRelativeTo(null);
		
		JTP.add("게시판", contentPanel);
		add(JTP);
		
		contentPanel.modifyButton.addActionListener(this);
		contentPanel.deleteButton.addActionListener(this);
		contentPanel.listButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == contentPanel.modifyButton) {
			String modifiedTitle = contentPanel.titleField.getText();
			String modifiedContent = contentPanel.contentArea.getText();
			String date = contentPanel.PKofContent;
			Log log = Log.getInstance();
			String tempID = log.getId();
			if(contentPanel.id.equals(tempID)) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
					String sql = "update boardContents set title = ?, content = ? where date = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, modifiedTitle);
					pstmt.setString(2, modifiedContent);
					pstmt.setString(3, date);
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modified!");
					dispose();
					new UserFrame().setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else JOptionPane.showMessageDialog(null, "권한 없어 돌아가");
		}
		
		if(e.getSource() == contentPanel.deleteButton) {
			Log log = Log.getInstance();
			String tempID = log.getId();
			String date = contentPanel.PKofContent;
			if(contentPanel.id.equals(tempID)) { 
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
					String sql = "delete from boardContents where date = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, date);
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Delete!");
					dispose();
					new UserFrame().setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else JOptionPane.showMessageDialog(null, "권한 없어 돌아가");
		}
		
		if(e.getSource() == contentPanel.listButton) {
			dispose();
			new UserFrame().setVisible(true);
		}
	}
}