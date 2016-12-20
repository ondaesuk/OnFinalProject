import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminTab4 extends JPanel{
	JPanel logoutPanel = new JPanel();
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	
	JButton logoutButton = new JButton("Log-Out");
	public AdminTab4() {
		setLayout(new GridLayout(7, 1));
		logoutPanel.add(logoutButton);
		add(logoutPanel);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
	}
}
