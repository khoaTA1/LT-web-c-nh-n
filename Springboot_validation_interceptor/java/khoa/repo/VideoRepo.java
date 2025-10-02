package khoa.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import khoa.entity.Video;

@Repository
public interface VideoRepo extends JpaRepository<Video, Integer> {
	List<Video> findByVideoNameContaining(String name);
	Page<Video> findByVideoNameContaining(String name, Pageable pageable);
	Page<Video> findAllByCateid(int cateid, Pageable pageable);
}
