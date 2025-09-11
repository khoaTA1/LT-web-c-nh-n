package DAO;

import java.lang.management.ThreadInfo;

import Config.JPAconfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class AbstractDAO<T> {
	private Class<T> ent;
	
	public void insert(T ent) {
		EntityManager EM = JPAconfig.getEntityManager();
		EntityTransaction trans = EM.getTransaction();
		
		try {
			trans.begin();
			EM.persist(ent);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			EM.close();
		}
	}
}
