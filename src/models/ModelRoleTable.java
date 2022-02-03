package models;

public class ModelRoleTable {

	int id;
	String roleName;
	int numUsersRole;

	public ModelRoleTable(int id, String roleName, int numUsersRole) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.numUsersRole = numUsersRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getNumUsersRole() {
		return numUsersRole;
	}

	public void setNumUsersRole(int numUsersRole) {
		this.numUsersRole = numUsersRole;
	}

}
