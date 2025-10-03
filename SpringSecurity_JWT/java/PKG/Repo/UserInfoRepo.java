package PKG.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import PKG.entity.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{
	Optional<UserInfo> findByName(String name);
}
