package khoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import khoa.interceptor.AccessControllInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
    private AccessControllInterceptor accessControlInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Đăng ký interceptor phân quyền
        registry.addInterceptor(accessControlInterceptor)
                .addPathPatterns("/**") // Áp dụng cho tất cả các request
                .excludePathPatterns("/general/login", "/general/register"); // Không áp dụng cho trang đăng nhập và đăng ký
    }
}
