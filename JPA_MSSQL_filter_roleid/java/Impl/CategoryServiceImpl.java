package Impl;

import java.io.File;
import java.util.List;

import DAO.CategoryDaoImpl;
import Entity.Category;
import Service.CategoryService;
import DAO.CategoryDao;

public class CategoryServiceImpl implements CategoryService {
	//CategoryDao categoryDao = new CategoryDaoImpl();
	CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}
	
	@Override
	public void edit(Category category) {
		Category oldCategory = categoryDao.findById(category.getId());
		
		if (oldCategory.getImages() != null) {
			final String oldIconName = oldCategory.getImages();
			final String dir = "C:\\Users\\khoat\\Documents\\HOC\\LT_web\\save\\1a\\KN_CSDL\\src\\main\\resources\\upload\\categoryIcons";
			
			File oldIcon = new File(dir + oldIconName);
			
			if (oldIcon.exists()) {
				oldIcon.delete();
			}
		}
		
		categoryDao.edit(category);
	}
	
	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}
	
	@Override
	public Category get(int id) {
		return categoryDao.findById(id);
	}
	
	@Override
	public Category get(String name) {
		return categoryDao.findByName(name);
	}
	
	@Override
	public List<Category> getAll(int rowEachPage, int startIndex) {
		return categoryDao.findAll(rowEachPage, startIndex);
	}
	
	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}
	
	@Override
	public long countRecord() {
		return categoryDao.countRecord();
	}
}
