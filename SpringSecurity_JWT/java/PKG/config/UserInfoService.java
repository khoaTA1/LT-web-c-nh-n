package PKG.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import PKG.Repo.UserInfoRepo;
import PKG.entity.UserInfo;

@Service
public class UserInfoService implements UserDetailsService {
	@Autowired 
	UserInfoRepo userrepo;
	
	public UserInfoService(UserInfoRepo userinforepo) {
		this.userrepo = userinforepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userrepo.findByName(uname);
		
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found: " + uname));
	}
}
