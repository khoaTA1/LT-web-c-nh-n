package sv_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import KN.KN_SQLSV;
import Model.User;
import service.UserDao;

public class UserDaoImpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement prs = null;
	public ResultSet rs = null;

	@Override
	public User get(String username) {
		String sql = "SELECT * FROM [profile_users] WHERE userName = ? ";
		try {
			conn = new KN_SQLSV().getConnection();
			prs = conn.prepareStatement(sql);
			prs.setString(1, username);
			rs = prs.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("Date"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void insert(User user) {
		String sql_qu = "insert into [profile_users](username, password, email, fullname, roleid, phone, Date, avatar) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			conn = new KN_SQLSV().getConnection();
			prs = conn.prepareStatement(sql_qu);
			prs.setString(1, user.getUserName());
			prs.setString(2, user.getPassWord());
			prs.setString(3, user.getEmail());
			prs.setString(4, user.getFullName());
			prs.setInt(5, user.getRoleid());
			prs.setString(6, user.getPhone());
			prs.setDate(7, user.getCreatedDate());
			prs.setString(8, user.getAvatar());
			
			prs.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean checkExistUsern(String usern) {
		// boolean dupl = false;
		
		String sql_qu = "select * from [profile_users] where userName = ?;";
		
		try {
			conn = new KN_SQLSV().getConnection();
			prs = conn.prepareStatement(sql_qu);
			
			prs.setString(1, usern);
			
			rs = prs.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			prs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean checkExistEmail(String email) {
		// boolean dupl = false;
		
		String sql_qu = "select * from [profile_users] where email = ?;";
		
		try {
			conn = new KN_SQLSV().getConnection();
			prs = conn.prepareStatement(sql_qu);
			
			prs.setString(1, email);
			
			rs = prs.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			prs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean checkExistPhone(String phone) {
		// boolean dupl = false;
		
		String sql_qu = "select * from [profile_users] where phone = ?;";
		
		try {
			conn = new KN_SQLSV().getConnection();
			prs = conn.prepareStatement(sql_qu);
			
			prs.setString(1, phone);
			
			rs = prs.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			prs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
