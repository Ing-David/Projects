package Action;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import java.util.ArrayList;
import Manage_Disc.*;
import javax.swing.GroupLayout.Alignment;
@SuppressWarnings("serial")
public class ListMember extends JFrame {

	JTable table;
	ArrayList<Member> memberlist;
	
	public ListMember(ArrayList<Member> memberlist) throws SQLException{
		String[] columnNames = {"ID", "Name","Phone Number"};
		
		int size = memberlist.size();
		Object[][] data = new Object[size][3];
		
		
		for(int i=0; i<size; i++){
			
			data[i][0] = String.valueOf(memberlist.get(i).getMid());
			data[i][1] = memberlist.get(i).getName();
			data[i][2] = memberlist.get(i).getPhoneNumber();			
		}
		
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(710, 480));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
	}

}
