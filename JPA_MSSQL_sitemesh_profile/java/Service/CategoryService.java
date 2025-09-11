package Service;

import java.util.List;

import Entity.Category;

public interface CategoryService {
	void insert(Category category);
	void edit(Category category);
	void delete(int id);
	Category get(int id);
	Category get(String name);
	List<Category> getAll(int rowEachPage, int startIndex);
	List<Category> getAll(int rowEachPage, int startIndex, int uid);
	List<Category> search(String keyword);
	long countRecord();
	long countRecord(int Uid);
}
