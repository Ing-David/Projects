package Action;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Manage_Disc.System_Management;
import javax.swing.GroupLayout.Alignment;


public class Modify {

	JFrame frame;
	private JTextField input_name;
	private JTextField input_phonenumber;
	private JLabel lblNewLabel_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modify window = new Modify();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void modify() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modify window = new Modify();
					
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
	public Modify() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddMember = new JLabel("Modify Member");
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
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Enter Member Name:");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btn_submit = new JButton("Modify");
		btn_submit.setForeground(Color.BLUE);
		btn_submit.setBackground(Color.WHITE);
		btn_submit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System_Management sm = new System_Management();
				Connection con = null;
				
				try {
					con = System_Management.openConnection();

					
						String sql = "select mid from modifyrecord where record ='yes'";
						Statement stm = con.createStatement();
						ResultSet rs = stm.executeQuery(sql);
						//int mid = rs.getInt(1);
					if(rs.next()) {
						int mid = rs.getInt(1);
						sm.updateMember(input_name.getText(), input_phonenumber.getText(), mid);
						String sql1 = "UPDATE modifyrecord set record = 'no' where record = 'yes'";
						String sql2 = "UPDATE memberborrow set name = '"+input_name.getText()+"', phonenumber = '"+input_phonenumber.getText()+"' where mid = '"+mid+"'";
						stm.executeUpdate(sql2);
						stm.executeUpdate(sql1);
						JOptionPane.showMessageDialog(btn_submit, "Member has been modified!!");
						frame.dispose();
						new ModifyMember().findMember();
					}
				} catch(SQLException | NumberFormatException | ClassNotFoundException e) {
					e.printStackTrace();
			    }
			}});
		
		input_phonenumber = new JTextField();
		input_phonenumber.setColumns(11);
		Connection con = null;

		try {
			con = System_Management.openConnection();
			String query = "select * from member where mid in (select mid from modifyrecord where record = 'yes')";
			Statement stm1= con.createStatement();
			ResultSet res = stm1.executeQuery(query);
			while(res.next()) {
				String name = res.getString("name");
				String phonenumber = res.getString("phonenumber");
				
				input_name.setText(name);
				input_phonenumber.setText(phonenumber);
			}
			
			
		} catch(SQLException | NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
	    }
		
		
		lblNewLabel_2 = new JLabel("Enter Phone Number:");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
				con = System_Management.openConnection();
				String query = "UPDATE modifyrecord set record = 'no' where record = 'yes'";
				Statement stm= con.createStatement();
				 stm.executeUpdate(query);
				frame.dispose();
				new ModifyMember().findMember();
				}catch(SQLException | NumberFormatException | ClassNotFoundException e) {
					e.printStackTrace();
			    }
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(237)
					.addComponent(lblAddMember, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(135)
					.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(272)
					.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(272)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(input_name, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_phonenumber, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
					.addGap(138))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblAddMember, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(input_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(input_phonenumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(47)
					.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
		
	}

