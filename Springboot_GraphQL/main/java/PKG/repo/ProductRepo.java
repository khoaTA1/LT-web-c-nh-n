package PKG.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

import PKG.entity.Category;
import PKG.entity.Product;

@GraphQlRepository
public interface ProductRepo extends JpaRepository<Product, Long> {
	//Tìm Kiếm theo nội dung tên
	Page<Product> findAllByCategory(Category cate, Pageable pageable);
	
	Page<Product> findAllByUserId(int userid, Pageable pageable);
	
	List<Product> findAllByOrderByPriceAsc();
	
	List<Product> findAllByCategory(Category cate);
}
