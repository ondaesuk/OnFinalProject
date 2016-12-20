import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserTab2 extends JPanel implements ActionListener {
	
	JPanel infoForChangePanel = new JPanel();
	JPanel nameForChangePanel = new JPanel(new FlowLayout());
	JPanel passForChangePanel = new JPanel(new FlowLayout());
	JPanel commentForChangePanel = new JPanel();
	JPanel confirmPanel = new JPanel();
	
	JTextField nameField = new JTextField(10);
	JPasswordField passField = new JPasswordField(10);
	JTextArea commentArea = new JTextArea(3, 40);
	JTextField confirmIDField = new JTextField(10);
	JPasswordField confirmPassField = new JPasswordField(10);
	JButton confirmButton = new JButton("Confirm");

	public UserTab2() {
		setLayout(new BorderLayout());
		confirmButton.addActionListener(this);
		
		nameForChangePanel.add(new JLabel("New Name : "));
		nameForChangePanel.add(nameField);
		passForChangePanel.add(new JLabel("New Password : "));
		passForChangePanel.add(passField);
		commentForChangePanel.add(new JLabel("New Comment : "));
		commentArea.setLineWrap(true);
		commentForChangePanel.add(new JScrollPane(commentArea));
		commentForChangePanel.setLayout(new FlowLayout());
		
		infoForChangePanel.setLayout(new GridLayout(3,  1));
		infoForChangePanel.add(nameForChangePanel);
		infoForChangePanel.add(passForChangePanel);
		infoForChangePanel.add(commentForChangePanel);
		
		confirmPanel.add(new JLabel("ID : "));
		confirmPanel.add(confirmIDField);
		confirmPanel.add(new JLabel("Password : "));
		confirmPanel.add(confirmPassField);
		confirmPanel.add(confirmButton);
		confirmPanel.setBorder(BorderFactory.createTitledBorder("confirm"));
		
		add(infoForChangePanel, BorderLayout.CENTER);
		add(confirmPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == confirmButton) {
			String tempName = nameField.getText();
			String tempPass = passField.getText();
			String tempReason = commentArea.getText();
			Log log = Log.getInstance();
			String tempID = log.getId();
			
			String confirmID = confirmIDField.getText();
			String confirmPass = confirmPassField.getText();

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
					String sql = "update personinfo set name = ?, pass = ?, reason = ? where id = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, tempName);
					pstmt.setString(2, tempPass);
					pstmt.setString(3, tempReason);
					pstmt.setString(4, tempID);
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modified!");
				} else JOptionPane.showMessageDialog(null, "¾ÈµÅ µ¹¾Æ°¡");
				nameField.setText(null);
				passField.setText(null);
				commentArea.setText(null);
				confirmIDField.setText(null);
				confirmPassField.setText(null);
			} catch (Exception e1){
				e1.printStackTrace();
			}
		}
	}

}