package PKG.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import PKG.Repo.UserInfoRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserInfoRepo repo;
	
	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoService(repo);
	}

    private final UserInfoService userInfoService;
	

    SecurityConfig(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

	@Bean
	public UserDetailsService userDetailsService1(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("khoaadmin").password(encoder.encode("1234")).roles("ADMIN").build();
		
		UserDetails user = User.withUsername("khoauser").password(encoder.encode("1234")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
		
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/", "/hello", "/login").permitAll()
																					.requestMatchers("/user/new").permitAll()
																					.requestMatchers("/customer/**").authenticated())
																			.userDetailsService(userDetailsService())
																			.formLogin(form -> form.defaultSuccessUrl("/hello", true).permitAll()).build();
	}
	
	
}
