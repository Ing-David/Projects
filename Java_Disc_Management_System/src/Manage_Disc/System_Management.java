package Manage_Disc;
import java.sql.*;
import java.util.ArrayList;
import Manage_Disc.Borrow_Disc;
public class System_Management {
	ArrayList<Member> memberlist = new ArrayList<Member>();
	ArrayList<Disc> disclist = new ArrayList<Disc>();
	ArrayList<Borrow_Disc> borrow_disclist = new ArrayList<Borrow_Disc>();
	
	
	//connect to database
		public static Connection openConnection() throws SQLException, ClassNotFoundException, NumberFormatException {
			Class.forName("com.mysql.jdbc.Driver"); 
		    Connection connect;
		    connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/discmanagementsystem","root",""); 
		    return connect;
		}	

		
// List all members in the system
	@SuppressWarnings("finally")
	public ArrayList<Member> listMember() throws SQLException {
		
		Connection con = null;
		try {
			 con = openConnection();  
			Statement stm = con.createStatement();
			ResultSet rs=stm.executeQuery("select * from member");
			
			while(rs.next())  {
				
				Member member = new Member(rs.getInt("mid"), rs.getString("name"), rs.getString("phonenumber"));
				memberlist.add(member);
				}
			 con.close();
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return memberlist;
		}
		
	}
	
	// List all discs
	@SuppressWarnings("finally")
	public ArrayList<Disc> listDisc() throws SQLException {
		
		Connection con1 = null;
		try {
			con1 = openConnection();  
			Statement stm1 = con1.createStatement();
			ResultSet rs1=stm1.executeQuery("select * from disc");
			
			while(rs1.next())  {
				Disc disc = new Disc(rs1.getInt("id"), rs1.getString("title"), rs1.getString("status"));
				disclist.add(disc);
				}
			 con1.close();
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return disclist;
		}
		
	}
	
	//List borrow record
	@SuppressWarnings("finally")
	public ArrayList<Borrow_Disc> listMemberBorrow() throws SQLException {
		
		Connection con2 = null;
		try {
			con2 = openConnection();  
			Statement stm2 = con2.createStatement();
			ResultSet rs2=stm2.executeQuery("select * from memberborrow");
			
			while(rs2.next()) {
				Borrow_Disc borrow_disc = new Borrow_Disc(rs2.getInt("mid"),rs2.getString("name"), rs2.getString("phonenumber"),
						rs2.getInt("id"), rs2.getString("title"),rs2.getString("start_date"),
						rs2.getString("expire_date"), rs2.getString("status"));
				borrow_disclist.add(borrow_disc);
				}
			 con2.close();
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return borrow_disclist;
		}
		
	}
	

	//add member that borrow the disc
	public void addBorrowMember(Borrow_Disc info) {
		
		Connection con1 = null;
		try { 
			con1 = openConnection();
			Statement stm = con1.createStatement();
            stm.executeUpdate("INSERT INTO memberborrow " + 
            		"VALUES ('" + info.getMid() + "','" + info.getName() + "','" + info.getPhoneNumber()
            		 +"','"+info.getId()+"','"+ info.getTitle() +"','"+info.getStart_date()+"','"+info.getExpire_date()+"','"+null+"')"); 
           stm.executeUpdate("update memberborrow set status = 'On Rent' where mid ='"+ info.getMid() +
        		   "' and id='"+ info.getId() +"' and start_date = '"+info.getStart_date()+"' and expire_date = '"+info.getExpire_date()+"'");
	       stm.executeUpdate("update disc set status = 'On Rent' where id='"+info.getId()+"'") ;
		
		
            con1.close(); 
        } catch (Exception e) { 
            System.err.println("Got an exception!"); 
            System.err.println(e.getMessage());
        }
	}
	
	//search for info of borrowed disc by the member
	public Borrow_Disc searchBorrow(int mid){
			
		Connection con = null;
		Borrow_Disc borrow = null;
		try {
			con = openConnection(); 
			Statement stm = con.createStatement();
			ResultSet rs=stm.executeQuery("select * from memberborrow where mid=" + mid);
			if(rs==null) {
				return null;
			}
			else if(rs.next()) {
				borrow = new Borrow_Disc(rs.getInt("mid"),rs.getString("name"),rs.getString("phonenumber"),rs.getInt("id"),
						rs.getString("title"),rs.getString("start_date"),rs.getString("expire_date"),rs.getString("status"));
			 con.close();
			 return borrow;
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			return null;
		}
		return borrow;	
		}
	
	
	
	
	
	//add member to memberlist
	public void addMember(Member member) {
		
		Connection con = null;
		try { 
			con = openConnection();
			Statement stm = con.createStatement();
            stm.executeUpdate("INSERT INTO member " + 
                "VALUES ('"+member.getMid()+"', '"+member.getName() + "', '" + member.getPhoneNumber() +"')"); 
            con.close(); 
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        }
	}
	
	
	// add new discs
	public void addDisc(Disc disc) {
		
		Connection con1 = null;
		try { 
			con1 = openConnection();
			Statement stm = con1.createStatement();
			
			 stm.executeUpdate("INSERT INTO disc " + 
	            		"VALUES ('" +disc.getId()+"','"+disc.getTitle() +"','"+disc.getStatus()+"')");  
			 String query1 = "update disc set status = 'inStock' where id = '"+disc.getId()+"'";
			 stm.executeUpdate(query1);
            con1.close(); 
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        }
	}
	
	//Remove member 
	public void deleteMember(int mid){
		
		Connection con = null;
		try {
			con = openConnection();
			String sql = "DELETE FROM member WHERE mid=?";
		    PreparedStatement preparedstatement = con.prepareStatement(sql);
		    preparedstatement.setInt(1, mid);
		    preparedstatement.executeUpdate();
		    con.close();
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
}
	
	
	//search member
	public Member searchMember(int mid){
			
		Connection con = null;
		Member member = null;
		try {
			con = openConnection(); 
			Statement stm = con.createStatement();
			ResultSet rs=stm.executeQuery("select * from member where mid=" + mid);
			if(rs==null) {
				return null;
			}
			else if(rs.next()) {	
				member = new Member(rs.getInt("mid"), rs.getString("name"), rs.getString("phonenumber"));
			    con.close();
			 return member; 
			 }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			return null;
		}
		return member;	
		}
	
	
	
	//search disc by id
	public Disc searchDisc(int id){
		Connection con = null;
		Disc disc = null;
		try {
			con = openConnection(); 
			Statement stm = con.createStatement();
			ResultSet rs=stm.executeQuery("select * from disc where id=" + id);
			if(rs==null) {
				return null;
			}
			else if(rs.next()){
							
				disc = new Disc(rs.getInt("id"), rs.getString("title"), rs.getString("status"));
			 con.close();
			}
			 //return disc; 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception f){
			f.printStackTrace();
		}
		return disc;
		}
	
	
	//Delete disc
	public void deleteDisc(int id){
		
		Connection con = null;
		try {
			con = openConnection();
			String sql = "DELETE FROM disc WHERE id=?";
		    PreparedStatement preparedstatement = con.prepareStatement(sql);
		    preparedstatement.setInt(1, id);
		    preparedstatement.executeUpdate();
		    con.close();
			 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	//update info of member
	public void updateMember(String name, String phonenumber, int id) {
		
		Connection con = null;
		try { 
			con = openConnection();
			Statement statement = con.createStatement();			
            statement.executeUpdate("UPDATE member SET name = '" + name + "', phonenumber = '" + phonenumber + "' WHERE mid =" + id); 		
            con.close(); 
        } catch (Exception e) { 
            System.err.println("Got an exception!"); 
            System.err.println(e.getMessage()); 
        } 
	}
	
}
