package PKG.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import PKG.entity.Category;
import PKG.repo.CategoryRepo;
import PKG.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepo categoryrepo;

	@Override
	public List<Category> findByCategoryNameContaining(String name) {
		return categoryrepo.findByCategoryNameContaining(name);
	}

	@Override
	public Page<Category> findByCategoryNameContaining(String name, Pageable pageable) {
		return categoryrepo.findByCategoryNameContaining(name, pageable);
	}

	@Override
	public <S extends Category> S save(S entity) {
		return categoryrepo.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return categoryrepo.findAll();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryrepo.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return categoryrepo.existsById(id);
	}

	@Override
	public long count() {
		return categoryrepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		categoryrepo.deleteById(id);
	}
	
	
}
