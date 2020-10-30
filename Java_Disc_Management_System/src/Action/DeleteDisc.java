package Action;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.SwingConstants;
import Manage_Disc.Disc;
import Manage_Disc.System_Management;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteDisc {

	JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void deleteDisc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDisc window = new DeleteDisc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteDisc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDeleteDiscFrom = new JLabel("Delete disc from the system");
		lblDeleteDiscFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteDiscFrom.setForeground(Color.RED);
		lblDeleteDiscFrom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JLabel label = new JLabel("Please complete the information below");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label.setBackground(new Color(0, 204, 0));
		
		JLabel lblEnterDiscId = new JLabel("Enter Disc ID:");
		lblEnterDiscId.setForeground(Color.BLUE);
		lblEnterDiscId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField = new JTextField();
		textField.setColumns(11);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				System_Management sm = new System_Management();
				
				Disc disc = sm.searchDisc(Integer.parseInt(textField.getText()));
				Connection con = null;
				try {
					con= System_Management.openConnection();
				if(disc != null) {
					String sql = "select id from memberborrow where status = 'On Rent' and id= '"+Integer.parseInt(textField.getText())+"'";
					Statement stm = con.createStatement();
					ResultSet rs = stm.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(btnDelete, "The Disc status is on rented!!");
						frame.dispose();
					}
					else {
					sm.deleteDisc(Integer.parseInt(textField.getText()));
					JOptionPane.showMessageDialog(btnDelete, "The Disc has been deleted from database");
					frame.dispose();
					new DeleteDisc().deleteDisc();
					//home.Back();
					}
				}else {
					JOptionPane.showMessageDialog(btnDelete, "ID NOT FOUND");
					
				}	
				}catch(SQLException | NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			    }
			}
		});
		
		JButton button_cancel = new JButton("Cancel");
		button_cancel.setForeground(Color.BLUE);
		button_cancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_cancel.setBackground(Color.WHITE);
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new HomePage().Back();
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(lblEnterDiscId, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(156, Short.MAX_VALUE)
					.addComponent(lblDeleteDiscFrom, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
					.addGap(150))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(288)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_cancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(288, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblDeleteDiscFrom, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnterDiscId, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(59)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(button_cancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(104, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
