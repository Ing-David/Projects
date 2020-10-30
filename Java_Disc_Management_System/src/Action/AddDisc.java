package Action;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import Manage_Disc.System_Management;
import Manage_Disc.Disc;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AddDisc {

	JFrame frame;
	JTextField input_title;
	JTextField input_id;

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
	/**
	 * @wbp.parser.entryPoint
	 */
	public void addDisc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDisc window = new AddDisc();
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
	public AddDisc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAddDisc = new JLabel("Add disc to system");
		lblAddDisc.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDisc.setForeground(Color.RED);
		lblAddDisc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAddDisc.setBackground(Color.BLACK);
		
		JLabel lblinfo = new JLabel("Please fill in the information below");
		lblinfo.setVerticalAlignment(SwingConstants.TOP);
		lblinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblinfo.setForeground(Color.GRAY);
		lblinfo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblinfo.setBackground(Color.BLACK);
		
		input_title = new JTextField();
		input_title.setBackground(Color.WHITE);
		input_title.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Disc ID:");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Enter Disc Title:");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	    input_id = new JTextField();
		input_id.setColumns(10);
		
		JButton btn_submit = new JButton("Add");
		btn_submit.setForeground(Color.BLUE);
		btn_submit.setBackground(Color.WHITE);
		btn_submit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn_submit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0){
				System_Management sm = new System_Management();
				Disc disc = new Disc(Integer.parseInt(input_id.getText()), (input_title.getText()),"yes");
				Connection con=null;
				try {
				con=System_Management.openConnection();
				Statement stm=con.createStatement();
				String query= "select * from disc where id='"+input_id.getText()+"' or title= '"+input_title.getText()+"'";
				ResultSet rs=stm.executeQuery(query);
				if(rs.next()) {
					JOptionPane.showMessageDialog(btn_submit, "ID and title might be comflict!!");
					frame.dispose();		
					new HomePage().Back();
			    }
				else {
					sm.addDisc(disc);
					JOptionPane.showMessageDialog(btn_submit, "Disc has been added to Server");
					frame.dispose();
					new AddDisc().addDisc();
				}
			  }catch(SQLException|NumberFormatException|ClassNotFoundException e) {
				  e.printStackTrace();
			  }
			}
		});
		
		
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
							.addComponent(lblAddDisc, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(input_id, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(272)
							.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(272)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(lblNewLabel_1)
							.addGap(29)
							.addComponent(input_title, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(129, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblAddDisc, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblinfo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(input_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(input_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(54)
					.addComponent(btn_submit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
