package models;

public class ModelTicketTable {

	int itemId;
	String itemBarCode;
	String itemName;
	String itemType;
	String size;
	double itemQuantity;
	double itemUtPrice;
	double itemPrice;
	
	public ModelTicketTable(int itemId, String itemBarCode, String itemName, String itemType, String size,
			double itemQuantity, double itemUtPrice, double itemPrice) {
		super();
		this.itemId = itemId;
		this.itemBarCode = itemBarCode;
		this.itemName = itemName;
		this.itemType = itemType;
		this.size = size;
		this.itemQuantity = itemQuantity;
		this.itemUtPrice = itemUtPrice;
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

	public double getItemQuantity() {
		return itemQuantity;
	}

	public String getStrItemQuantity() {
		return String.valueOf(itemQuantity);
	}
	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getItemUtPrice() {
		return itemUtPrice;
	}
	
	public String getStrItemUtPrice() {
		return String.valueOf(itemUtPrice);
	}

	public void setItemUtPrice(double itemUtPrice) {
		this.itemUtPrice = itemUtPrice;
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
