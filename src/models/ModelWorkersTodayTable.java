package models;

public class ModelWorkersTodayTable {

	String scheduleName;
	String userName;

	public ModelWorkersTodayTable(String scheduleName, String userName) {
		super();
		this.scheduleName = scheduleName;
		this.userName = userName;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
