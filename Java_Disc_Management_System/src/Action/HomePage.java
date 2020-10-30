package Action;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import Manage_Disc.System_Management;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HomePage {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Back(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	public HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcomeToDisc = new JLabel("Welcome to Disc Management System");
		lblWelcomeToDisc.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToDisc.setForeground(Color.RED);
		lblWelcomeToDisc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblWelcomeToDisc.setBackground(Color.BLACK);
		
		JLabel lblPleaseSelectOne = new JLabel("Please select one option below");
		lblPleaseSelectOne.setBackground(new Color(0, 204, 0));
		lblPleaseSelectOne.setVerticalAlignment(SwingConstants.TOP);
		lblPleaseSelectOne.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPleaseSelectOne.setForeground(Color.GRAY);
		lblPleaseSelectOne.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnListDisc = new JButton("List Disc");
		btnListDisc.setBackground(Color.ORANGE);
		btnListDisc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System_Management sm= new System_Management();
				ListDisc gui;
				try {
					gui = new ListDisc(sm.listDisc());
					gui.setSize(710,480);
					gui.setBounds(100, 100, 709, 431);
					gui.setVisible(true);
					gui.setTitle("Disc List Result");
				//	frame.dispose();
				} catch (SQLException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnListDisc);
		
		JButton btnAddDisc = new JButton("Add Disc");
		btnAddDisc.setBackground(Color.ORANGE);
		btnAddDisc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddDisc().addDisc();
				frame.dispose();
			//	new HomePage().Back();
			}
		
		});
	frame.getContentPane().add(btnAddDisc);
		
		JButton btnListMember = new JButton("List Member");
		
		
		btnListMember.setBackground(Color.ORANGE);
		btnListMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System_Management sm = new System_Management();
				ListMember gui;
				try {
					gui = new ListMember(sm.listMember());
					gui.setSize(710,480);
					gui.setBounds(100, 100, 709, 431);
					gui.setVisible(true);
					gui.setTitle("Member Result");
					//frame.dispose();
				} catch (SQLException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
					
			}
		});
		
		JButton btnListBorrowMember = new JButton("Borrow Record");
		btnListBorrowMember.setBackground(Color.ORANGE);
		btnListBorrowMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnListBorrowMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System_Management sm = new System_Management();
				ListMemberBorrow gui;
				try {
					gui = new ListMemberBorrow(sm.listMemberBorrow());
					gui.setSize(710,480);
					gui.setBounds(100, 100, 709, 431);
					gui.setVisible(true);
					gui.setTitle("Borrow Record");
					//frame.dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		frame.getContentPane().add(btnListBorrowMember);
		
		
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.setBackground(Color.ORANGE);
		btnAddMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddMember().addMember();
				frame.dispose();
				
			}
		});
		
		JButton btnAddMemberBorrow = new JButton("Add MemberBorrow");
		btnAddMemberBorrow.setBackground(Color.ORANGE);
		btnAddMemberBorrow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddMemberBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddMemberBorrow().addMemberBorrow();
				frame.dispose();
			}
		});
		
		JButton btnDeleteBorrowmember = new JButton("Modify Record");
		btnDeleteBorrowmember.setBackground(Color.ORANGE);
		btnDeleteBorrowmember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteBorrowmember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteMemberBorrow().deleteMemberBorrow();
				frame.dispose();
				
			}
		});
		frame.getContentPane().add(btnDeleteBorrowmember);
		
		JButton btnDeleteDisc = new JButton("Delete Disc");
		btnDeleteDisc.setBackground(Color.ORANGE);
		btnDeleteDisc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteDisc().deleteDisc();
				frame.dispose();				
			}
		});
		frame.getContentPane().add(btnDeleteDisc);
		
		
		
		JButton btnFindMember = new JButton("Find Member");
		btnFindMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFindMember.setBackground(Color.ORANGE);
		btnFindMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindMember().findMember();
			}
		});
		frame.getContentPane().add(btnFindMember);
		
		JButton btnFindDisc = new JButton("Find Disc");
		btnFindDisc.setBackground(Color.ORANGE);
		btnFindDisc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFindDisc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindDisc().findDisc();
			}
		});
		
		JButton btnDeleteMember = new JButton("Delete Member");
		btnDeleteMember.setBackground(Color.ORANGE);
		btnDeleteMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteMember().deleteMember();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnDeleteMember);
		
		JButton btnModifyMember = new JButton("Modify Member");
		btnModifyMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ModifyMember().findMember();
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnModifyMember);
		btnModifyMember.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnModifyMember.setBackground(Color.ORANGE);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBackground(Color.ORANGE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWelcomeToDisc, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPleaseSelectOne, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(btnListDisc, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(123)
							.addComponent(btnAddDisc, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(btnListMember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(123)
							.addComponent(btnAddMember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(btnListBorrowMember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(123)
							.addComponent(btnAddMemberBorrow, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addComponent(btnDeleteBorrowmember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(123)
							.addComponent(btnDeleteDisc, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(96)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnFindMember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDeleteMember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
							.addGap(123)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnModifyMember, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFindDisc, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))))
					.addGap(3))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(254, Short.MAX_VALUE)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(248))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblWelcomeToDisc)
					.addGap(6)
					.addComponent(lblPleaseSelectOne)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnListDisc, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddDisc, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnListMember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddMember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnListBorrowMember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddMemberBorrow, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDeleteBorrowmember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteDisc, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFindMember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFindDisc, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDeleteMember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModifyMember, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
