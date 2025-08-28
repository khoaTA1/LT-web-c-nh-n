package sv_impl;

import Model.User;
import service.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService{
	UserDao userdao = new UserDaoImpl();
	
	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}
	
	@Override
	public User get(String username) {
		return userdao.get(username);
	}
}
