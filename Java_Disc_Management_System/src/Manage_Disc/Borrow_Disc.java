package Manage_Disc;

public class Borrow_Disc {
	private int id;
	private String name;
	private String phonenumber;
	private int mid;
	private String title;
	private String start_date;
	private String expire_date;
	private String status;
	
	public Borrow_Disc(int mid,String name, String phonenumber, int id, String title,String start_date,String expire_date,String status) {
		super();
		this.mid = mid;	
		this.name = name;
		this.phonenumber = phonenumber;
		this.id = id;
		this.title = title;
		this.start_date=start_date;
		this.expire_date=expire_date;
		this.status=status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phonenumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phonenumber = phoneNumber;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getExpire_date() {
		return expire_date;
	}
	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getStatus() {
		return status;
	}
	public void setAvailable(String status) {
		this.status=status;
	}

}
