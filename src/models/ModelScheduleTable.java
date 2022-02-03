package models;

public class ModelScheduleTable {

	int id;
	String scheduleName;
	String checkInTime;
	String checkOutTime;

	public ModelScheduleTable(int id, String scheduleName, String checkInTime, String checkOutTime) {
		super();
		this.id = id;
		this.scheduleName = scheduleName;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

}
