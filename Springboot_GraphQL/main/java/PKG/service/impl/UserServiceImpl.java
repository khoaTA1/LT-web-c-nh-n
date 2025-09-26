package PKG.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PKG.entity.User;
import PKG.repo.UserRepo;
import PKG.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userrepo;

	@Override
	public <S extends User> S save(S entity) {
		return userrepo.save(entity);
	}

	@Override
	public List<User> findAll() {
		return userrepo.findAll();
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userrepo.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return userrepo.existsById(id);
	}

	@Override
	public long count() {
		return userrepo.count();
	}

	@Override
	public void deleteById(Integer id) {
		userrepo.deleteById(id);
	}
	
	
}
