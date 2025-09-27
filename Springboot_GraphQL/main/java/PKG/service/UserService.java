package PKG.service;

import java.util.List;
import java.util.Optional;

import PKG.entity.User;

public interface UserService {

	void deleteById(Integer id);

	long count();

	boolean existsById(Integer id);

	Optional<User> findById(Integer id);

	List<User> findAll();

	<S extends User> S save(S entity);

	Optional<User> findByEmail(String email);

}
