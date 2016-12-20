import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame implements ActionListener {
	final String administratorID = "ondaesuk";
	final String administratorPASS = "ondaesuk";

	JPanel idPanel = new JPanel();
	JPanel passPanel = new JPanel();
	JPanel logInPanel = new JPanel();

	JTextField idTF = new JTextField(10);
	JPasswordField passPF = new JPasswordField(10);

	JButton logInButton = new JButton("Log-In");

	public LogIn() {
		setTitle("Log-In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 150);
		setLayout(new GridLayout(3, 1));
		setLocationRelativeTo(null);

		idPanel.add(new JLabel("      ID : "));
		idPanel.add(idTF);
		passPanel.add(new JLabel("Pass : "));
		passPanel.add(passPF);
		logInPanel.add(logInButton);
		logInButton.addActionListener(this);

		add(idPanel);
		add(passPanel);
		add(logInPanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String adminID = administratorID;
		String adminPass = administratorPASS;
		String tempID = idTF.getText();
		String tempPass = passPF.getText();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
			String sql = "select * from dbperson.personinfo where id = "+"'"+tempID+"'"+" and pass = "+"'"+tempPass+"'";
			System.out.println(sql);
			Statement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			if(rs.next()) {
				Log log = Log.getInstance();
				log.setId(tempID);
				log.setPassword(tempPass);
				System.out.println(tempID + tempPass);
				dispose();
				new UserFrame().setVisible(true);
			} else if(tempID.equals(adminID) && tempPass.equals(adminPass)) {
				dispose();
				new AdminFrame().setVisible(true);
			} else JOptionPane.showMessageDialog(null, "¾ÈµÅ µ¹¾Æ°¡");
		} catch (Exception e1){
			e1.printStackTrace();
		}
	}
}
