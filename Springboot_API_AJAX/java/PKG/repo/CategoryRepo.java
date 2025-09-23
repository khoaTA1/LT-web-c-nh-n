package PKG.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PKG.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	List<Category> findByCategoryNameContaining(String name);
	Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
	Page<Category> findAllByUid(int uid, Pageable pageable);
	Optional<Category> findByCategoryName(String name);
}
