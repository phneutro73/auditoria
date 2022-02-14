package models;

public class ModelShopTable {

	int id;
	String shopName;
	String shopDirection;
	int numUsersShop;

	public ModelShopTable(int id, String shopName, String shopDirection, int numUsersShop) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.shopDirection = shopDirection;
		this.numUsersShop = numUsersShop;
	}

	public int getId() {
		return id;
	}
	
	public String getStrId() {
		return String.valueOf(id);
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
	
	public int getNumUsersShop() {
		return numUsersShop;
	}
	
	public String getStrNumUsersShop() {
		return String.valueOf(numUsersShop);
	}
	
	public void setNumUsersShop(int numUsersShop) {
		this.numUsersShop = numUsersShop;
	}

}
