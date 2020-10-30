package Action;

import java.sql.SQLException;
import Manage_Disc.Disc;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class ListDisc extends JFrame {

	JTable table;
	JFrame frame;
	ArrayList<Disc> disclist;
	
	public ListDisc(ArrayList<Disc> disclist) throws SQLException{
		String[] columnNames = {"ID", "Title","Available"};
		int size = disclist.size();
		Object[][] data = new Object[size][3] ;
		
		for(int i=0; i<size; i++){	
			data[i][0] = String.valueOf(disclist.get(i).getId());
			data[i][1] = disclist.get(i).getTitle();
			data[i][2] = disclist.get(i).getStatus();
		}
		
	
	
		table = new JTable(data, columnNames);
		//table.setPreferredSize(710);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
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
