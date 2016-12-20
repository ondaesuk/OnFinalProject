import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminTab3 extends JPanel implements ActionListener {
	JPanel deletePanel = new JPanel();
	
	JTextField deleteField = new JTextField(10);
	
	JButton deleteButton = new JButton("Delete");
	
	public AdminTab3() {
		setLayout(new GridLayout(3, 1));
		
		deleteButton.addActionListener(this);
		
		deletePanel.add(new JLabel("ID"));
		deletePanel.add(deleteField);
		deletePanel.add(deleteButton);
		add(deletePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == deleteButton) {
			String deleteID = deleteField.getText();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
				Statement sql = DB.createStatement();
				
				int result = JOptionPane.showConfirmDialog(null, "Are you sure you delete?\n"+deleteID, "Delete", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					int check = sql.executeUpdate("delete from personInfo where id = '"+deleteID+"'");
					if(check == 1) {
						JOptionPane.showMessageDialog(null, "Delete!\n"+deleteID);
						deleteField.setText(null);
					}
					else {
						JOptionPane.showMessageDialog(null, "¾ÈµÅ µ¹¾Æ°¡\n"+deleteID);
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}