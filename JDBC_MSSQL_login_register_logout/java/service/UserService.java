package service;

import Model.User;

public interface UserService {
	User login(String username, String password);
	User get(String username);
	void insert(User user);
	boolean register(String email, String password, String usern, String fulln, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsern(String usern);
	boolean checkExistPhone(String phone);
}
