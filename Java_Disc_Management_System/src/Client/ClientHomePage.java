package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import Action.ListMemberBorrow;
import Manage_Disc.Borrow_Disc;
import Manage_Disc.System_Management;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientHomePage{

	private JFrame frame;
	private JTextField input_to_view;
	JTable table;
	ArrayList<Borrow_Disc> memberlist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientHomePage window = new ClientHomePage();
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
	public ClientHomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcomeToMember = new JLabel("Welcome to Member Page");
		lblWelcomeToMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToMember.setForeground(Color.RED);
		lblWelcomeToMember.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblWelcomeToMember.setBackground(Color.BLACK);
		
		JLabel lblPleaseEnterThe = new JLabel("Please enter the require information below");
		lblPleaseEnterThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterThe.setForeground(Color.GRAY);
		lblPleaseEnterThe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPleaseEnterThe.setBackground(new Color(0, 204, 0));
		
		JLabel lblEnterYourOwn = new JLabel("Enter your own ID:");
		lblEnterYourOwn.setForeground(Color.BLUE);
		lblEnterYourOwn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_to_view = new JTextField();
		input_to_view.setColumns(10);
		
		JButton btnViewYourRecord = new JButton("View Your Record");
		btnViewYourRecord.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewYourRecord.setBackground(Color.ORANGE);
		btnViewYourRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System_Management sm = new System_Management();
				ArrayList<Borrow_Disc> memberlist = new ArrayList<Borrow_Disc>();
				ListMemberBorrow gui;
				
					try {
						int mid = Integer.parseInt(input_to_view.getText());
						memberlist.add(sm.searchBorrow(mid));
						gui = new ListMemberBorrow(memberlist);
						
						gui.setSize(640,300);
						gui.setVisible(true);
						gui.setTitle("Your record");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						JOptionPane.showMessageDialog( frame, "ID NOT FOUND");
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblPleaseEnterThe, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addComponent(lblEnterYourOwn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(input_to_view, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(266, Short.MAX_VALUE)
					.addComponent(btnViewYourRecord, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(253))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(lblWelcomeToMember, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblWelcomeToMember, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPleaseEnterThe, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterYourOwn, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_to_view, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addComponent(btnViewYourRecord, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(186, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
