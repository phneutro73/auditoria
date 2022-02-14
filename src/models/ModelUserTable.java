package models;

public class ModelUserTable {

	int id;
	String name;
	String surname;
	String dni;
	String dob;
	String roleName;
	String shopName;

	public ModelUserTable(int id, String name, String surname, String dni, String dob, String roleName, String shopName) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.dob = dob;
		this.roleName = roleName;
		this.shopName = shopName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}
