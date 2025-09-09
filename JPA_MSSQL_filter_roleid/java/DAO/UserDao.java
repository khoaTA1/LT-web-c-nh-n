package DAO;

import Entity.User;

public interface UserDao {
	User findByUsername(String username);
	void insert(User user);
	boolean checkExistUsern(String usern);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phone);
	
	boolean changePassword(String new_passw, String email);
}
