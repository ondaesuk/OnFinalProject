import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ContentPanel extends JPanel {
	JPanel northPanel = new JPanel();
	JPanel titlePanel = new JPanel();
	JPanel namePanel = new JPanel();
	JPanel datePanel = new JPanel();
	JPanel contentPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	JTextField titleField = new JTextField(50);
	JTextField nameField = new JTextField(50);
	JTextField dateField = new JTextField(50);
	JTextArea contentArea = new JTextArea(20, 50);
	
	JButton modifyButton = new JButton("Modify");
	JButton deleteButton = new JButton("Delete");
	JButton listButton = new JButton("List");
	
	String PKofContent = null;
	String title = null;
	String id = null;
	String content = null;
	
	public ContentPanel(String tempPK) {
		this.PKofContent = tempPK;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
			Statement sql = DB.createStatement();
			ResultSet cursor = sql.executeQuery("select title, id, content from boardContents where date = '" + PKofContent + "'");
			while(cursor.next()) {
				title = cursor.getString(1);
				id = cursor.getString(2);
				content = cursor.getString(3);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		titlePanel.add(new JLabel("    제목 : "));
		titleField.setText(title);
		titlePanel.add(titleField);
		
		namePanel.add(new JLabel("작성자 : "));
		nameField.setText(id);
		nameField.setEditable(false);
		namePanel.add(nameField);
		
		datePanel.add(new JLabel("    날짜 : "));
		dateField.setText(PKofContent);
		dateField.setEditable(false);
		datePanel.add(dateField);
		
		contentPanel.add(new JLabel("    내용 : "));
		contentArea.setLineWrap(true);
		contentArea.setText(content);
		contentPanel.add(new JScrollPane(contentArea));
		
		buttonPanel.add(modifyButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(listButton);
		
		northPanel.setLayout(new GridLayout(3, 1));
		northPanel.add(titlePanel);
		northPanel.add(namePanel);
		northPanel.add(datePanel);
		
		add(northPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
