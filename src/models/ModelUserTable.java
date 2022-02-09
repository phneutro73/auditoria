package models;

public class ModelUserTable {

	int id;
	String name;
	String surname;
	String dni;
	String dob;
	String roleId;

	public ModelUserTable(int id, String name, String surname, String dni, String dob, String roleId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.dob = dob;
		this.roleId = roleId;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
