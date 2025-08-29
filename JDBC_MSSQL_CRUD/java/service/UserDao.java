package service;

import Model.User;

public interface UserDao {
	User get(String username);
	void insert(User user);
	boolean checkExistUsern(String usern);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phone);
	
	boolean changePassword(String new_passw, String email);
}
