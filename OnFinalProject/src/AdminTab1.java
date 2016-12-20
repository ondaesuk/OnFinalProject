import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminTab1 extends JPanel implements ActionListener {
	JPanel namePanel = new JPanel();
	JPanel mailPanel = new JPanel();
	JPanel passPanel = new JPanel();
	JPanel sexPanel = new JPanel();
	JPanel datePanel = new JPanel();
	JPanel commentPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JTextField nameField = new JTextField(10);
	JTextField mailField = new JTextField(15);
	JTextField passField = new JTextField(10);
	JTextArea commentArea = new JTextArea(3, 40);
	JButton joinButton = new JButton("Sign-Up");
	JButton clearButton = new JButton("Clear");
	
	JRadioButton manRadioButton = new JRadioButton("Man");
	JRadioButton womanRadioButton = new JRadioButton("Woman");
	ButtonGroup buttonGroup = new ButtonGroup();
	
	Date dateV = new Date(System.currentTimeMillis());
	SimpleDateFormat dateModel = new SimpleDateFormat("yyyy-MM-dd");
		
	public AdminTab1() {
		setLayout(new GridLayout(7, 1));
		
		joinButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		buttonGroup.add(manRadioButton);
		buttonGroup.add(womanRadioButton);
		
		namePanel.add(new JLabel("Name : "));
		namePanel.add(nameField);
		mailPanel.add(new JLabel("E-mail(ID) : "));
		mailPanel.add(mailField);
		passPanel.add(new JLabel("Password : "));
		passPanel.add(passField);
		sexPanel.add(manRadioButton);
		sexPanel.add(womanRadioButton);
		datePanel.add(new JLabel("Date : "));
		datePanel.add(new JLabel(dateModel.format(dateV)));
		commentArea.setLineWrap(true);
		commentPanel.add(new JLabel("Comment : "));
		commentPanel.add(new JScrollPane(commentArea));
		commentArea.setLineWrap(true);
		buttonPanel.add(joinButton);
		buttonPanel.add(clearButton);
		
		add(namePanel);
		add(mailPanel);
		add(passPanel);
		add(sexPanel);
		add(datePanel);
		add(commentPanel);
		add(buttonPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == joinButton) {
			String name = nameField.getText();
			String id = mailField.getText();
			String pass = passField.getText();
			String sex = null;
			if(manRadioButton.isSelected()) sex = "남자"; 
			if(womanRadioButton.isSelected()) sex = "여자";
			String date = dateModel.format(dateV);
			String reason = commentArea.getText();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
				Statement sql = DB.createStatement();
				sql.executeUpdate(String.format("insert into personInfo (id, name, pass, sex, date, reason) values ('%s', '%s', '%s', '%s', '%s', '%s')", id, name, pass, sex, date, reason));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Complete!", "Signed-Up", 1);
			nameField.setText(null);
			mailField.setText(null);
			passField.setText(null);
			buttonGroup.clearSelection();
			commentArea.setText(null);
		}
		
		if(e.getSource() == clearButton) {
			nameField.setText(null);
			mailField.setText(null);
			passField.setText(null);
			buttonGroup.clearSelection();
			commentArea.setText(null);
		}
	}
}
