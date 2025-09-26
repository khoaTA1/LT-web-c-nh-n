package PKG.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import PKG.entity.Category;
import PKG.entity.Product;
import PKG.repo.ProductRepo;
import PKG.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepo productrepo;

	@Override
	public <S extends Product> S save(S entity) {
		return productrepo.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productrepo.findAll();
	}

	@Override
	public List<Product> findAllByCategory(Category cate) {
		return productrepo.findAllByCategory(cate);
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

	@Override
	public Optional<Product> findById(Long id) {
		return productrepo.findById(id);
	}

	@Override
	public Page<Product> findAllByUserId(int userid, Pageable pageable) {
		return productrepo.findAllByUserId(userid, pageable);
	}

	@Override
	public List<Product> findAllByOrderByPriceAsc() {
		return productrepo.findAllByOrderByPriceAsc();
	}
	
}
