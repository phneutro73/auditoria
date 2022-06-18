package models;

public class ModelShopTable {

	int id;
	String shopName;
	String shopDirection;
	String shopCity;
	String shopProvince;
	String shopCp;
	String shopCountry;
	int numUsersShop;

	public ModelShopTable(int id, String shopName, String shopDirection, String shopCity, String shopProvince,
			String shopCp, String shopCountry, int numUsersShop) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.shopDirection = shopDirection;
		this.shopCity = shopCity;
		this.shopProvince = shopProvince;
		this.shopCp = shopCp;
		this.shopCountry = shopCountry;
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

	public String getShopCity() {
		return shopCity;
	}

	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}

	public String getShopProvince() {
		return shopProvince;
	}

	public void setShopProvince(String shopProvince) {
		this.shopProvince = shopProvince;
	}

	public String getShopCp() {
		return shopCp;
	}

	public void setShopCp(String shopCp) {
		this.shopCp = shopCp;
	}

	public String getShopCountry() {
		return shopCountry;
	}

	public void setShopCountry(String shopCountry) {
		this.shopCountry = shopCountry;
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
