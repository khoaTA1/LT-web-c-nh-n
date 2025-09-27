package PKG.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import PKG.entity.User;

@GraphQlRepository
public interface UserRepo extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}
