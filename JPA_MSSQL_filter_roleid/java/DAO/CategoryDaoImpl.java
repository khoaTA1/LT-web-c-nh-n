package DAO;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Config.JPAconfig;
import Entity.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import DAO.CategoryDao;

public class CategoryDaoImpl implements CategoryDao {
	
	@Override
	public void insert(Category category) {
		EntityManager EM = JPAconfig.getEntityManager();
		EntityTransaction trans = EM.getTransaction();
		
		try {
			trans.begin();
			EM.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			EM.close();
		}
	}
	
	@Override
	public void edit(Category category) {
		EntityManager EM = JPAconfig.getEntityManager();
		EntityTransaction trans = EM.getTransaction();
		
		try {
			trans.begin();
			
			Category ent = EM.find(Category.class, category.getId());
			
			ent.setCategoryName(category.getCategoryName());
			ent.setUid(category.getUid());
			ent.setImages(category.getImages());
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			EM.close();
		}
	}
	
	@Override
	public void delete(int id) {
		EntityManager EM = JPAconfig.getEntityManager();
		EntityTransaction trans = EM.getTransaction();
		
		try {
			trans.begin();
			Category ent = EM.find(Category.class, id);
			
			if (ent != null) {
				EM.remove(ent);
			}
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			EM.close();
		}
	}
	
	@Override
	public Category findById(int id) {
		EntityManager EM = JPAconfig.getEntityManager();
		
		try {
			Category ent = EM.find(Category.class, id);
			
			return ent;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
		
		return null;
	}
	
	@Override
	public Category findByName(String name) {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT o FROM Category o WHERE o.categoryName = :name";
		
		try {
			TypedQuery<Category> query = EM.createQuery(jpql, Category.class);
			
			Category ent = query.getSingleResult();
			
			return ent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Category> findAll(int rowEachPage, int startIndex) {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT o FROM Category o";
		
		try {
			TypedQuery<Category> query = EM.createQuery(jpql, Category.class);
			query.setFirstResult(startIndex);
			query.setMaxResults(rowEachPage);
			List<Category> list = query.getResultList();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
		
		return null;
	}
	
	@Override
	public List<Category> search(String keyword) {
		
		return null;
	}
	
	@Override
	public long countRecord() {
		EntityManager EM = JPAconfig.getEntityManager();
		String jpql = "SELECT COUNT(o) FROM Category o";
		
		try {
			Query query = EM.createQuery(jpql);
			
			return (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
