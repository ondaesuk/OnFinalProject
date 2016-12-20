import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class UserFrame extends JFrame implements ActionListener, MouseListener {
	JTabbedPane JTP = new JTabbedPane();
	UserTab1 userTab1 = new UserTab1();
	UserTab2 userTab2 = new UserTab2();
	UserTab3 userTab3 = new UserTab3();
	UserTab4 userTab4 = new UserTab4();
	
	public UserFrame() {
		Log log = Log.getInstance();
		String id = log.getId();
		setTitle("User - " + id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 589);
		setLocationRelativeTo(null);
		userTab1.writeButton.addActionListener(this);
		userTab1.boardTable.addMouseListener(this);
		userTab3.confirmButton.addActionListener(this);
		userTab4.logoutButton.addActionListener(this);
		
		JTP.add("°Ô½ÃÆÇ", userTab1);
		JTP.add("Á¤º¸¼öÁ¤", userTab2);
		JTP.add("È¸¿øÅ»Åð", userTab3);
		JTP.add("·Î±×¾Æ¿ô", userTab4);
		
		add(JTP);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = userTab1.boardTable.getSelectedRow();
		String tempPK = (String) userTab1.boardTable.getValueAt(row, 3);
		System.out.println(tempPK);
		dispose();
		new UserFrameContent(tempPK).setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == userTab1.writeButton) {
			dispose();
			new UserFrameWrite().setVisible(true);
		}
		if(e.getSource() == userTab4.logoutButton) {
			Log log = Log.getInstance();
			log.setId(null);
			log.setPassword(null);
			dispose();
			new LogIn().setVisible(true);
		}
		if(e.getSource() == userTab3.confirmButton) {
			String confirmID = userTab3.IDField.getText();
			String confirmPass = userTab3.passField.getText();
			Log log = Log.getInstance();
			String tempID = log.getId();
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
					String sql1 = "select * from dbperson.personinfo where id = "+"'"+confirmID+"'"+" and pass = "+"'"+confirmPass+"'";
					System.out.println(sql1);
					Statement pstmt1 = conn1.prepareStatement(sql1);
					ResultSet rs = pstmt1.executeQuery(sql1);
					if(rs.next()) {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
						String sql = "delete from personinfo where id = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tempID);
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Exit!");
						log.setId(null);
						log.setPassword(null);
						dispose();
						new LogIn().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "¾ÈµÅ µ¹¾Æ°¡");
						userTab3.IDField.setText(null);
						userTab3.passField.setText(null);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}