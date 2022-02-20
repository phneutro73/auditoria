package models;

import java.util.Date;

public class ModelHistoricalSalesDataTable {

	int itemId;
	String itemBarCode;
	String user;
	String shop;
	double price;
	Date date;

	public ModelHistoricalSalesDataTable(int itemId, String itemBarCode, String user, String shop, double price,
			Date date) {
		super();
		this.itemId = itemId;
		this.itemBarCode = itemBarCode;
		this.user = user;
		this.shop = shop;
		this.price = price;
		this.date = date;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public String getStrItemId() {
		return String.valueOf(itemId);
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemBarCode() {
		return itemBarCode;
	}

	public void setItemBarCode(String itemBarCode) {
		this.itemBarCode = itemBarCode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public double getPrice() {
		return price;
	}

	public String getStrPrice()  {
		return String.valueOf(price);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}
	
	public String getStrDate() {
		return String.valueOf(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
