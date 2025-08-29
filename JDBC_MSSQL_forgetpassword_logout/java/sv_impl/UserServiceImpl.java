package sv_impl;

import java.sql.Date;

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
	
	
	public boolean checkExistUsern(String usern) {
		return userdao.checkExistUsern(usern);
	}
	
	
	public boolean checkExistEmail(String email) {
		return userdao.checkExistEmail(email);
	}
	
	
	public boolean checkExistPhone(String phone) {
		return userdao.checkExistPhone(phone);
	}
	
	@Override
	public void insert(User user) {
		userdao.insert(user);
	}
	
	@Override
	public boolean register(String email, String usern, String fulln, String password, String phone) {
		if (userdao.checkExistUsern(usern)) {
			return false;
		}
		
		long time_Millis = System.currentTimeMillis();
		Date createddate = new Date(time_Millis);
		
		userdao.insert(new User(email, usern, fulln, password, null, 5, phone, createddate));
		return true;
	}
	
	public boolean changePassword(String new_passw, String email) {
		return userdao.changePassword(new_passw, email);
	}
}
