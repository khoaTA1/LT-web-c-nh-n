package khoa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import khoa.entity.Category;
import khoa.entity.Video;

public interface VideoService {

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	Optional<Video> findById(Integer id);

	List<Video> findAll();

	<S extends Video> S save(S entity);

	Page<Video> findAllByCateid(int cateid, Pageable pageable);

	List<Video> findByVideoNameContaining(String name);

}
