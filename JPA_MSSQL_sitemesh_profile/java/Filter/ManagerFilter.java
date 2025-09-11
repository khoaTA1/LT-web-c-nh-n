package Filter;

import java.io.IOException;

import Entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/views/manager/*")
public class ManagerFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		if (session != null) {
			User user = (User) session.getAttribute("account");

			if (user != null && user.getRoleid() == 2) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + "/views/general/login.jsp");
			}
		}
	}

	public void destroy() {
	}
}
