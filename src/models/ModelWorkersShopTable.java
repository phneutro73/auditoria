package models;

public class ModelWorkersShopTable {

	String id;
	String userName;

	public ModelWorkersShopTable(String id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
