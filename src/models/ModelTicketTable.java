package models;

public class ModelTicketTable {

	int itemId;
	String itemBarCode;
	String itemName;
	String itemType;
	String size;
	double itemPrice;
	
	public ModelTicketTable(int itemId, String itemBarCode, String itemName, String itemType, String size,
			double itemPrice) {
		super();
		this.itemId = itemId;
		this.itemBarCode = itemBarCode;
		this.itemName = itemName;
		this.itemType = itemType;
		this.size = size;
		this.itemPrice = itemPrice;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getItemPrice() {
		return itemPrice;
	}
	
	public String getStrItemPrice() {
		return String.valueOf(itemPrice);
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
