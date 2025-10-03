package PKG.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import PKG.Repo.UserInfoRepo;
import PKG.entity.UserInfo;

@Service
public record UserService(UserInfoRepo repo, PasswordEncoder passwordEncoder) {
	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repo.save(userInfo);
		
		return "Thêm user thành công";
	}
}
