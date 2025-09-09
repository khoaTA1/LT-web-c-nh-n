package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Config.JPAconfig;
import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import DAO.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByUsername(String username) {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT o FROM User o WHERE o.userName = :uname";
		
		try {
			TypedQuery<User> query = EM.createQuery(jpql,User.class);
			
			query.setParameter("uname", username);
			
			User ent = query.getSingleResult();
			
			return ent;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
		
		return null;
	}

	@Override
	public void insert(User user) {
		EntityManager EM = JPAconfig.getEntityManager();
		EntityTransaction trans = EM.getTransaction();
		
		try {
			trans.begin();
			EM.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			EM.close();
		}
	}

	@Override
	public boolean checkExistUsern(String usern) {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT o FROM User o WHERE o.userName = :uname";

		try {
			TypedQuery<User> query = EM.createQuery(jpql,User.class);
			
			query.setParameter("uname", usern);
			// tối ưu hiệu suất: chỉ cần trả về 1 kết quả, không cần trả hết
			query.setMaxResults(1);
			
			List<User> list = query.getResultList();
			
			if (list.isEmpty()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
		
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT o FROM User o WHERE o.email = :email";

		try {
			TypedQuery<User> query = EM.createQuery(jpql,User.class);
			
			query.setParameter("email", email);
			// tối ưu hiệu suất: chỉ cần trả về 1 kết quả, không cần trả hết
			query.setMaxResults(1);
			
			List<User> list = query.getResultList();
			
			if (list.isEmpty()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
		
		return true;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT o FROM User o WHERE o.phone = :phone";

		try {
			TypedQuery<User> query = EM.createQuery(jpql,User.class);
			
			query.setParameter("phone", phone);
			// tối ưu hiệu suất: chỉ cần trả về 1 kết quả, không cần trả hết
			query.setMaxResults(1);
			
			List<User> list = query.getResultList();
			
			if (list.isEmpty()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
		
		return true;
	}

	@Override
	public boolean changePassword(String new_passw, String email) {
		String jpql = "SELECT o FROM User o WHERE o.email = :email";
		EntityManager EM = JPAconfig.getEntityManager();
		EntityTransaction trans = EM.getTransaction();
		
		try {
			trans.begin();
			TypedQuery<User> query = EM.createQuery(jpql,User.class);
			
			query.setParameter("email", email);
			query.setMaxResults(1);
			List<User> list = query.getResultList();
			
			if (!list.isEmpty()) {
				User ent = list.get(0);
				
				ent.setPassWord(new_passw);
				trans.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			EM.close();
		}
		
		return false;
	}
}
