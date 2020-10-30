package Action;

import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.*;
import Manage_Disc.Borrow_Disc;
import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class ListMemberBorrow extends JFrame {

	JPanel contentPane;
	JTable table;
	ArrayList<Borrow_Disc> memberlist;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ListMemberBorrow(ArrayList<Borrow_Disc> memberlist) throws SQLException {
		
		
		String[] columnNames = {"mid", "name" ,"phonenumber", "id", "title","start_date","expire_date","Status"};
		int size = memberlist.size();
		Object[][] data = new Object[size][8];
		
		
		for(int i=0; i<size; i++){
			data[i][0] = String.valueOf(memberlist.get(i).getMid());
			data[i][1] = String.valueOf(memberlist.get(i).getName());
			data[i][2] = memberlist.get(i).getPhoneNumber();
			data[i][3] = memberlist.get(i).getId();
			data[i][4] = String.valueOf(memberlist.get(i).getTitle());
			data[i][5] = String.valueOf(memberlist.get(i).getStart_date());
			data[i][6] = String.valueOf(memberlist.get(i).getExpire_date());
			data[i][7] = String.valueOf(memberlist.get(i).getStatus());
		}
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(710, 480));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
	}
}
