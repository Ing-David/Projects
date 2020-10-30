package Manage_Disc;

public class Person {
	private int mid;
	private String name;
	private String phonenumber;
	
	public Person(int mid, String name, String phonenumber) {
		super();
		this.mid = mid;
		this.name = name;
		this.phonenumber = phonenumber;
	}
	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

	public void setPhoneNumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
