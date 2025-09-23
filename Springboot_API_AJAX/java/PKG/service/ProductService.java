package PKG.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import PKG.entity.Product;

public interface ProductService {

	void deleteById(Long id);

	long count();

	boolean existsById(Long id);

	Page<Product> findAllByCategory(String Category, Pageable pageable);

	Optional<Product> findById(Long id);

	<S extends Product> S save(S entity);

	Optional<Product> findByProductName(String name);

	List<Product> findByProductNameContaining(String name);

}
