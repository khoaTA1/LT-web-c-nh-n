package service;

import Model.User;

public interface UserDao {
	User get(String username);
}
