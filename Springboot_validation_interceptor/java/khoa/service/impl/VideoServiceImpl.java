package khoa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import khoa.entity.Category;
import khoa.entity.Video;
import khoa.repo.VideoRepo;
import khoa.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	VideoRepo videorepo;

	@Override
	public List<Video> findByVideoNameContaining(String name) {
		return videorepo.findByVideoNameContaining(name);
	}

	@Override
	public Page<Video> findAllByCateid(int cateid, Pageable pageable) {
		return videorepo.findAllByCateid(cateid, pageable);
	}

	@Override
	public <S extends Video> S save(S entity) {
		return videorepo.save(entity);
	}

	@Override
	public List<Video> findAll() {
		return videorepo.findAll();
	}

	@Override
	public Optional<Video> findById(Integer id) {
		return videorepo.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return videorepo.existsById(id);
	}

	@Override
	public long count() {
		return videorepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		videorepo.deleteById(id);
	}

	
}
