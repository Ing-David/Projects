package Action;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Manage_Disc.Borrow_Disc;
import Manage_Disc.System_Management;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.sql.*;
public class AddMemberBorrow {

	private JFrame frame;
	private JTextField input_name;
	private JTextField input_mid;
	private JTextField input_phonenumber;
	private JLabel lblNewLabel_2;
	private JTextField input_id;
	private JTextField input_title;
	private JTextField input_startdate;
	private JTextField input_expiredate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberBorrow window = new AddMemberBorrow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addMemberBorrow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemberBorrow window = new AddMemberBorrow();
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
	public AddMemberBorrow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddMember = new JLabel("Add member that borrows the disc");
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
				int mid= Integer.parseInt(input_mid.getText());
				String name= input_name.getText();
				String phonenumber=input_phonenumber.getText();
				int id= Integer.parseInt(input_id.getText());
				String title=input_title.getText();
				
				Borrow_Disc member = new Borrow_Disc(mid,name,phonenumber,id,title,input_startdate.getText(),input_expiredate.getText(),null);
				Connection con=null;
				
				try { 
					  con=System_Management.openConnection();
					  if(mid!=0 && name!=null && phonenumber!=null) {
						  String sql= "select * from member where mid='"+ mid +"' and name='"+ name +"' and phonenumber='"+ phonenumber +"'";
						  Statement stm = con.createStatement();
						ResultSet rs=stm.executeQuery(sql);
					      if(rs.next()) {
					    	 if(id!=0 && title!=null) {
					    	 String sql1= "select * from disc where id='"+id+"' and title='"+title+"'";
					    	 rs=stm.executeQuery(sql1);
					    	 	if(rs.next()){
					    	 		String sql2= "select * from memberborrow where status = 'On Rent' ";
					    	 		 rs=stm.executeQuery(sql2);
					    	 		if(rs.next()) {
					    	 			String sql3="select * from disc where status = 'On Rent' and id='"+id+"'";
					    	 			rs=stm.executeQuery(sql3);
					    	 			if(rs.next()) {
												String sql4= "select mid from memberborrow where status = 'On Rent' and mid="+ mid;
							    	 			rs=stm.executeQuery(sql4);
							    	 				if(rs.next()) {
							    	 					JOptionPane.showMessageDialog(btn_submit, "You can not borrow anymore!!");
							    	 					frame.dispose();
							    	 					new HomePage().Back();
					    	 			}
					    	 			else {
					    	 				JOptionPane.showMessageDialog(btn_submit, "Disc is not available!!");
											frame.dispose();
											new HomePage().Back();
					    	 					}
					    	 			}
					    	 			}
					    	 		else {
					    	 		sm.addBorrowMember(member);
					    	 		JOptionPane.showMessageDialog(btn_submit, "The Member has been added to borrowlist");
					    	 		frame.dispose();
					    	 		new AddMemberBorrow().addMemberBorrow();
					    	 		} 
					    	 	}
					    	 else {
					    		 JOptionPane.showMessageDialog(btn_submit, "Invalid disc");
									frame.dispose();
					    	 }
					       }
					    	 else {
					    		 JOptionPane.showMessageDialog(btn_submit, "Fill all required!!");
									frame.dispose();
					    	 }
					     }
					      else {
					    	  JOptionPane.showMessageDialog(btn_submit, "Invalid member");
								frame.dispose();
					      }
					  }
					  else {
						    JOptionPane.showMessageDialog(btn_submit, "Fill all required!!");
							frame.dispose();
					  }
				}catch(SQLException | NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			    }
			}
		});
		
		input_phonenumber = new JTextField();
		input_phonenumber.setColumns(11);
		
		lblNewLabel_2 = new JLabel("Enter Phone Number:");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblEnterDiscId = new JLabel("Enter Disc ID:");
		lblEnterDiscId.setForeground(Color.BLUE);
		lblEnterDiscId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_id = new JTextField();
		input_id.setColumns(11);
		
		JLabel lblEnterDiscTitle = new JLabel("Enter Disc Title:");
		lblEnterDiscTitle.setForeground(Color.BLUE);
		lblEnterDiscTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_title = new JTextField();
		input_title.setColumns(11);
		
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
		
		JLabel lblEnterStartDate = new JLabel("Enter Start Date:");
		lblEnterStartDate.setForeground(Color.BLUE);
		lblEnterStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_startdate = new JTextField();
		input_startdate.setColumns(11);
		
		JLabel lblEnterExpireDate = new JLabel("Enter Expire Date:");
		lblEnterExpireDate.setForeground(Color.BLUE);
		lblEnterExpireDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_expiredate = new JTextField();
		input_expiredate.setColumns(11);
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(input_mid, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(input_name, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_phonenumber, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterDiscId, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_id, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterDiscTitle, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_title, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterStartDate, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_startdate, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterExpireDate, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(input_expiredate, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(133)
							.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(18, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(385, Short.MAX_VALUE)
					.addComponent(lblAddMember, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
					.addGap(172))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(354, Short.MAX_VALUE)
					.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
					.addGap(137))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblAddMember, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(lblNewLabel_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(input_mid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(input_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(input_phonenumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterDiscId)
						.addComponent(input_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterDiscTitle)
						.addComponent(input_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterStartDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_startdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterExpireDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_expiredate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
	
