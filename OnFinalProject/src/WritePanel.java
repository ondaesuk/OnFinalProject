import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WritePanel extends JPanel {
	JPanel titlePanel = new JPanel();
	JPanel contentPanel = new JPanel();
	JPanel completePanel = new JPanel();
	
	JTextField titleField = new JTextField(50);
	JTextArea contentArea = new JTextArea(20, 50);
	JButton completeButton = new JButton("Complete");
	JButton cancelButton = new JButton("Cancel");
	
	public WritePanel() {
		setLayout(new BorderLayout());
		
		titlePanel.add(new JLabel("제목 : "));
		titlePanel.add(titleField);
		contentArea.setLineWrap(true);
		contentPanel.add(new JLabel("내용 : "));
		contentPanel.add(new JScrollPane(contentArea));
		
		completePanel.add(completeButton);
		completePanel.add(cancelButton);
		
		add(titlePanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(completePanel, BorderLayout.SOUTH);
	}

}
