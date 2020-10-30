package Action;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import Manage_Disc.Borrow_Disc;
import Manage_Disc.System_Management;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteMemberBorrow {

	JFrame frame;
	private JTextField input_mid_borrow;

	/**
	 * Launch the application.
	 */
	
	public void deleteMemberBorrow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteMemberBorrow window = new DeleteMemberBorrow();
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
	public DeleteMemberBorrow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Modify Record");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPleaseCompleteThe = new JLabel("Please complete the information below");
		lblPleaseCompleteThe.setVerticalAlignment(SwingConstants.TOP);
		lblPleaseCompleteThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseCompleteThe.setForeground(Color.GRAY);
		lblPleaseCompleteThe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPleaseCompleteThe.setBackground(new Color(0, 204, 0));
		
		JLabel label = new JLabel("Enter Member ID:");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		input_mid_borrow = new JTextField();
		input_mid_borrow.setColumns(11);
		
		JButton btnDelete = new JButton("Modify");
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System_Management sm = new System_Management();	
			    Borrow_Disc borrowmember= sm.searchBorrow(Integer.parseInt(input_mid_borrow.getText()));
			    Connection con =null;
			    try {
					con = System_Management.openConnection();
			    if(borrowmember != null) {
					
				//	sm.deleteBorrow(Integer.parseInt(input_mid_borrow.getText()));
					
					Statement stm = con.createStatement();
					String sql = "update memberborrow set status = 'Retreived' where status = 'On Rent' and mid ='"+input_mid_borrow.getText()+"'";
					stm.executeUpdate(sql);
					String sql1= "update disc set status = 'inStock' where status = 'On Rent' and id in(select id from memberborrow where mid ='"+input_mid_borrow.getText()+"')";
					stm.executeUpdate(sql1);
					JOptionPane.showMessageDialog(btnDelete, "Borrow Record has been update!");
					frame.dispose();
					new DeleteMemberBorrow().deleteMemberBorrow();
					
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(148)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(input_mid_borrow, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(261)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_cancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(140, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblPleaseCompleteThe, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPleaseCompleteThe, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(input_mid_borrow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(60)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(button_cancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
	}

}
