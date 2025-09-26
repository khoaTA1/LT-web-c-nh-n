package PKG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import PKG.entity.Category;
import PKG.entity.Product;

public interface ProductService {

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	List<Product> findAll();

	<S extends Product> S save(S entity);

	Page<Product> findAllByUserId(int userid, Pageable pageable);

	List<Product> findAllByOrderByPriceAsc();

	List<Product> findAllByCategory(Category cate);

	Optional<Product> findById(Long id);
	
}
