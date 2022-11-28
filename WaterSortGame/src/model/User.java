package model;

import DB.DataBase;

public class User {

	private static int userId;
	private String userName;
	private String userPassword;
	private DataBase db;
	
	public User() {
		db = new DataBase();
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getLevel() {
		return db.getLevel(userId);
	}
	
	public boolean validateUser() {
		int id = db.checkLogIn(userName, userPassword);
		
		if (id != 0) {
			userId = id;
			return true;
		}
		return false;
	}

}
