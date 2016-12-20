import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminFrame extends JFrame implements ActionListener {
	JTabbedPane JTP = new JTabbedPane();
	AdminTab1 adminTab1 = new AdminTab1();
	AdminTab2 adminTab2 = new AdminTab2();
	AdminTab3 adminTab3 = new AdminTab3();
	AdminTab4 adminTab4 = new AdminTab4();
	
	public AdminFrame() {
		setTitle("Administrator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 589);
		setLocationRelativeTo(null);
		JTP.add("회원등록", adminTab1);
		JTP.add("회원검색", adminTab2);
		JTP.add("회원삭제", adminTab3);
		JTP.add("로그아웃", adminTab4);
		add(JTP);
		adminTab4.logoutButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == adminTab4.logoutButton) {
			Log log = Log.getInstance();
			log.setId(null);
			log.setPassword(null);
			new LogIn().setVisible(true);
			dispose();
		}
	}
}
