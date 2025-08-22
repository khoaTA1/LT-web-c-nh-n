package tao_proj;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class LoginbyServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
		resp.setContentType("text/html");
		
		String usern=req.getParameter("usern");
		String pass=req.getParameter("pass");
		
		if (usern.equals("Khoa") && pass.equals("123456789")) {
			Cookie Login_cookie = new Cookie("username", usern);
			Login_cookie.setMaxAge(60);
			resp.addCookie(Login_cookie);
			resp.sendRedirect("/tao_proj/RCookie");
		} else {
			resp.sendRedirect("/tao_proj/Login");
		}
	}
}
