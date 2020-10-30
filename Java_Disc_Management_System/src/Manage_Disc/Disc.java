package Manage_Disc;

public class Disc {
	private int id;
	private String title;
	private String status;
	public Disc(int id, String title,String status) {
		this.id = id;
		this.title = title;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setAvailable(String status) {
		this.status = status;
	}
}
