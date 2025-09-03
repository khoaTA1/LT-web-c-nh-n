package sv_impl;

import java.io.File;
import java.util.List;

import Model.Category;
import service.CategoryService;
import service.CategoryDao;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}
	
	@Override
	public void edit(Category category) {
		Category oldCategory = categoryDao.get(category.getCateid());
		
		if (oldCategory.getIcon() != null) {
			final String oldIconName = oldCategory.getIcon();
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
		return categoryDao.get(id);
	}
	
	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}
	
	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}
	
	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}
}
