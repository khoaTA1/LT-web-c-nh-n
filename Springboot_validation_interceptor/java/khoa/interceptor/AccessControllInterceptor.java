package khoa.interceptor;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import khoa.entity.User;

@Component
public class AccessControllInterceptor implements HandlerInterceptor{
	
	public void postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");

        if (user != null) {
            if (user.getRoleid() == 1) { 
                
                if (request.getRequestURI().contains("/admin/")) {
                    response.sendRedirect("/user/home");  
                }
            } else if (user.getRoleid() == 2) { 
                if (request.getRequestURI().contains("/user/")) {
                    response.sendRedirect("/admin/home");  
                }
            }
        } else {
            
            response.sendRedirect("/login");
        }
	}
}
