package PKG.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import PKG.entity.Product;
import PKG.repo.ProductRepo;
import PKG.service.ProductService;

public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productrepo;

	@Override
	public List<Product> findByProductNameContaining(String name) {
		return productrepo.findByProductNameContaining(name);
	}

	@Override
	public Optional<Product> findByProductName(String name) {
		return productrepo.findByProductName(name);
	}

	@Override
	public <S extends Product> S save(S entity) {
		return productrepo.save(entity);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productrepo.findById(id);
	}

	@Override
	public Page<Product> findAllByCategory(String Category, Pageable pageable) {
		return productrepo.findAllByCategory(Category, pageable);
	}

	@Override
	public boolean existsById(Long id) {
		return productrepo.existsById(id);
	}

	@Override
	public long count() {
		return productrepo.count();
	}

	@Override
	public void deleteById(Long id) {
		productrepo.deleteById(id);
	}
	
	
}
