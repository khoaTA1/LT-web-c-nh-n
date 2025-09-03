package Controller;

import java.io.IOException;

import org.eclipse.tags.shaded.org.apache.bcel.classfile.Constant;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import sv_impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	public static final String SESSION_USERNAME = "username";
	public static final String COOKIE_REMEMBER = "username";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
		}
		
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean isRememberMe = false;
		String remember = req.getParameter("remember");
		
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
		
		String alertMsg = "";
		
		if (username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/wp_t1/login.jsp").forward(req, resp);
			return;
		}
		
		UserService service = new UserServiceImpl();
		User user = service.login(username, password);
		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if (isRememberMe) {
				saveRemeberMe(resp, username);
			}
			
			if (user.getUserName().equals("admin")) {
				resp.sendRedirect(req.getContextPath() + "/wp_t1/admin/admin-page.jsp");
			} else {
				resp.sendRedirect(req.getContextPath() + "/wp_t1/topbar.jsp");
			}
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/wp_t1/login.jsp").forward(req, resp);
		}
	}
	
	private void saveRemeberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(LoginController.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}
}