package main;

import Config.JPAconfig;
import Entity.Category;
import Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class test {
	public static void main(String[] args) {
		EntityManager EM = JPAconfig.getEntityManager();
		
		EntityTransaction trans = EM.getTransaction();
		
		Category category = new Category();
		User user = new User("khoatdmk2@gmail.com", "khoata", "Trương Anh Khoa", "12345", null, 1, "012345678", null);
		
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
}
