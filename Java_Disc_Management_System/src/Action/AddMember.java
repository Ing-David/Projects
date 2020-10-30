package Action;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Manage_Disc.Member;
import Manage_Disc.System_Management;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AddMember {

	JFrame frame;
	private JTextField input_name;
	private JTextField input_mid;
	private JTextField input_phonenumber;
	private JLabel lblNewLabel_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember window = new AddMember();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void addMember() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember window = new AddMember();
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
	public AddMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 710, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddMember = new JLabel("Add member to system");
		lblAddMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMember.setForeground(Color.RED);
		lblAddMember.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAddMember.setBackground(Color.BLACK);
		
		JLabel lblinfo = new JLabel("Please fill in the information below");
		lblinfo.setVerticalAlignment(SwingConstants.TOP);
		lblinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfo.setForeground(Color.GRAY);
		lblinfo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblinfo.setBackground(Color.BLACK);
		
		input_name = new JTextField();
		input_name.setBackground(Color.WHITE);
		input_name.setColumns(11);
		
		JLabel lblNewLabel = new JLabel("Enter Member ID:");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Enter Member Name:");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_mid = new JTextField();
		input_mid.setColumns(11);
		
		JButton btn_submit = new JButton("Add");
		btn_submit.setForeground(Color.BLUE);
		btn_submit.setBackground(Color.WHITE);
		btn_submit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System_Management sm = new System_Management();
				
				Member member = new Member( Integer.parseInt(input_mid.getText()), input_name.getText(), input_phonenumber.getText());
				sm.addMember(member);

				JOptionPane.showMessageDialog(btn_submit, "The Member has been added to Server");
				frame.dispose();
				new AddMember().addMember();
			}
		});
		
		input_phonenumber = new JTextField();
		input_phonenumber.setColumns(11);
		
		lblNewLabel_2 = new JLabel("Enter Phone Number:");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
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
							.addGap(237)
							.addComponent(lblAddMember, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(input_phonenumber, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
											.addComponent(input_name, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)))
									.addGap(129))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
										.addGap(49)
										.addComponent(input_mid, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(272)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))))
					.addGap(130))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblAddMember, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(input_mid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(input_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(input_phonenumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
		
	}

