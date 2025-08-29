package Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		ss.invalidate();
		
		resp.sendRedirect(req.getContextPath() + "/wp_t1/login.jsp");
		//req.getRequestDispatcher(req.getContextPath() + "/wp_t1/login.jsp").forward(req, resp);
	}
}