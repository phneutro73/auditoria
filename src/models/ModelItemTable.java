package models;

public class ModelItemTable {

	int id;
	String barCode;
	String itemName;
	String itemType;
	String itemSize;
	int quantity;
	int inShop;
	int reservations;
	double price;

	public ModelItemTable(int id, String barCode, String itemName, String itemType, String itemSize, int quantity,
			int inShop, int reservations, double price) {
		super();
		this.id = id;
		this.barCode = barCode;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemSize = itemSize;
		this.quantity = quantity;
		this.inShop = inShop;
		this.reservations = reservations;
		this.price = price;
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getStrQuantity() {
		return String.valueOf(quantity);
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getInShop() {
		return inShop;
	}

	public String getStrInShop() {
		return String.valueOf(inShop);
	}

	public void setInShop(int inShop) {
		this.inShop = inShop;
	}

	public int getReservations() {
		return reservations;
	}

	public String getStrReservation() {
		return String.valueOf(reservations);
	}

	public void setReservations(int reservations) {
		this.reservations = reservations;
	}

	public double getPrice() {
		return price;
	}

	public String getStrPrice() {
		return String.valueOf(price);
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
