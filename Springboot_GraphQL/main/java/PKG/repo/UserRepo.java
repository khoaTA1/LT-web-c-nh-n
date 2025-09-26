package PKG.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import org.springframework.stereotype.Repository;

import PKG.entity.User;

@GraphQlRepository
public interface UserRepo extends JpaRepository<User, Integer>{
	
}
