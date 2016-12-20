import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminTab2 extends JPanel implements ActionListener {
	JPanel searchPanel = new JPanel();
	JPanel tablePanel = new JPanel();
	
	
	JComboBox<String> tableBox = new JComboBox<>(new String[] {"ID", "이름", "성별", "가입일", "가입인사"});
	JTextField searchField = new JTextField(10);
	JButton searchButton = new JButton("Search");
	JTable table = new JTable();
	String[] tableFields = {"ID", "이름", "성별", "가입일", "가입인사"};
	DefaultTableModel tableModel = new DefaultTableModel(tableFields, 0);
	
	public AdminTab2() {
		setLayout(new BorderLayout());
		
		searchButton.addActionListener(this);
		
		tableModel.setRowCount(0);
		table.setModel(tableModel);
		
		searchPanel.add(tableBox);
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		tablePanel.add(new JScrollPane(table));
		
		add(searchPanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == searchButton) {
			tableModel.setRowCount(0);
			if(searchField.getText().equals("")) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
					Statement sql = DB.createStatement();
					ResultSet cursor = sql.executeQuery("select id, name, sex, date, reason from personInfo");
					while(cursor.next()) {
						String[] fieldData = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)};
						tableModel.addRow(fieldData);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				try {
					int i = tableBox.getSelectedIndex();
					String selectedMenu = null;
					switch(i) {
					case 0 : selectedMenu = "id"; break;
					case 1 : selectedMenu = "name"; break;
					case 2 : selectedMenu = "sex"; break;
					case 3 : selectedMenu = "date"; break;
					case 4 : selectedMenu = "reason"; break;
					}
					String search = searchField.getText().trim();
					Class.forName("com.mysql.jdbc.Driver");
					Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
					Statement sql = DB.createStatement();
					ResultSet cursor = sql.executeQuery("select id, name, sex, date, reason from personInfo where " + selectedMenu + " like '%" + search + "%'");
					while(cursor.next()) {
						String[] fieldData = {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)};
						tableModel.addRow(fieldData);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}