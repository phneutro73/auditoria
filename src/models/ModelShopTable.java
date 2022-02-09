package models;

public class ModelShopTable {

	int id;
	String shopName;
	String shopDirection;

	public ModelShopTable(int id, String shopName, String shopDirection) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.shopDirection = shopDirection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopDirection() {
		return shopDirection;
	}

	public void setShopDirection(String shopDirection) {
		this.shopDirection = shopDirection;
	}

}
