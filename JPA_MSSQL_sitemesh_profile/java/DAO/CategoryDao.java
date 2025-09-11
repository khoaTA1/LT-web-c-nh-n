package DAO;

import java.util.List;

import Entity.Category;

public interface CategoryDao {
	void insert(Category category);
	void edit(Category category);
	void delete(int id);
	Category findById(int id);
	Category findByName(String name);
	List<Category> findAll(int rowEachPage, int startIndex);
	List<Category> findAll(int rowEachPage, int startIndex, int uid);
	List<Category> search(String keyword);
	long countRecord();
	long countRecord(int Uid);
}
