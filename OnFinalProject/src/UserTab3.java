import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserTab3 extends JPanel {
	JPanel confirmPanel = new JPanel(new FlowLayout());

	JTextField IDField = new JTextField(10);
	JPasswordField passField = new JPasswordField(10);
	JButton confirmButton = new JButton("Confirm");

	public UserTab3() {
		setLayout(new BorderLayout());
		confirmPanel.setBorder(BorderFactory.createTitledBorder("confirm"));
		confirmPanel.add(new JLabel("ID : "));
		confirmPanel.add(IDField);
		confirmPanel.add(new JLabel("Pass : "));
		confirmPanel.add(passField);
		confirmPanel.add(confirmButton);
		add(confirmPanel, BorderLayout.NORTH);
	}
}
