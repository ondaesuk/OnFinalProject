import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UserTab1 extends JPanel implements ActionListener {
	private static final int PAGE_SIZE = 15;
	JButton writeButton = new JButton("Write Post");
	JButton nextButton = new JButton("Next");
	JButton prevButton = new JButton("Prev");
	int maxPageNo;
	private int currentPageNo;
	
	JTable boardTable = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"ID","捞抚","力格", "累己老矫"}));
	DefaultTableModel tableModel; 
	
	public UserTab1() {
		boardTable.setRowHeight(28);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		boardTable.getColumn("ID").setPreferredWidth(100);
		boardTable.getColumn("ID").setCellRenderer(celAlignCenter);
		boardTable.getColumn("捞抚").setPreferredWidth(50);
		boardTable.getColumn("捞抚").setCellRenderer(celAlignCenter);
		boardTable.getColumn("力格").setPreferredWidth(240);
		boardTable.getColumn("力格").setCellRenderer(celAlignCenter);
		boardTable.getColumn("累己老矫").setPreferredWidth(70);
		boardTable.getColumn("累己老矫").setCellRenderer(celAlignCenter);
		
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.add(writeButton);
		
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.add(prevButton);
		southPanel.add(nextButton);
		prevButton.addActionListener(this);
		nextButton.addActionListener(this);
		
		add(northPanel, BorderLayout.NORTH);
		add(new JScrollPane(boardTable), BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		calculateMaxPageNo();
		doLoad();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if(e.getSource().equals(prevButton)) doPrev();
		if(e.getSource().equals(nextButton)) doNext();
	}

	private void calculateMaxPageNo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection DB = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
			Statement sql = DB.createStatement();
			ResultSet cursor = sql.executeQuery("select count(*) from boardContents");
			cursor.first();
			maxPageNo = cursor.getInt(1) / PAGE_SIZE;
			cursor.close();
			DB.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doPrev() {
		if(currentPageNo == 0) return;
		currentPageNo--;
		doLoad();
	}

	private void doNext() {
		if(currentPageNo >= maxPageNo) return;
		currentPageNo++;
		doLoad();
	}

	private void doLoad() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection DB=DriverManager.getConnection("jdbc:mysql://localhost:3306/DBperson", "root", "931127");
			Statement sql=DB.createStatement();
			ResultSet cursor=sql.executeQuery("select id, name, title, date from boardContents limit "+currentPageNo*PAGE_SIZE+", "+PAGE_SIZE);
			DefaultTableModel tableModel = (DefaultTableModel) boardTable.getModel();
			tableModel.setRowCount(0);
			while(cursor.next()) {
				tableModel.addRow(new String[] {cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}
}