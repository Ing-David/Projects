package Action;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import Manage_Disc.Member;
import Manage_Disc.System_Management;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMember {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void deleteMember() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteMember window = new DeleteMember();
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
	public DeleteMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblDeleteMemberFrom = new JLabel("Delete Member from the system");
		//lblDeleteMemberFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteMemberFrom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblDeleteMemberFrom.setForeground(Color.RED);
		
		JLabel lblNewLabel = new JLabel("Please complete the information below");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		
		JLabel lblEnterMemberId = new JLabel("Enter Member ID:");
		lblEnterMemberId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterMemberId.setForeground(Color.BLUE);
		
		textField = new JTextField();
		textField.setColumns(11);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System_Management sm = new System_Management();
				Member member = sm.searchMember(Integer.parseInt(textField.getText()));
				Connection con = null;
				try {
					con = System_Management.openConnection();
					if(member != null) {
						String sql = "select mid from memberborrow where status = 'On Rent' and mid = '"+Integer.parseInt(textField.getText())+"'";
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery(sql);

						if(rs.next()) {
							JOptionPane.showMessageDialog(btnDelete, "The Disc status is on rented!! Can not delete member!!");
						}
						else {
							sm.deleteMember(Integer.parseInt(textField.getText()));
							JOptionPane.showMessageDialog(btnDelete, "The Member has been deleted from database");
							frame.dispose();
							new DeleteMember().deleteMember();
						}
					}
					else {
						JOptionPane.showMessageDialog(btnDelete, "The Member has not found!!");
						frame.dispose();
					}
				}catch(SQLException | NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			    }
				
				
			}
		});
		
	
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new HomePage().Back();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(196)
							.addComponent(lblDeleteMemberFrom))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(182)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addComponent(lblEnterMemberId)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(297)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel)
								.addComponent(btnDelete))))
					.addContainerGap(172, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblDeleteMemberFrom)
					.addGap(17)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnterMemberId)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnCancel)
					.addGap(125))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
