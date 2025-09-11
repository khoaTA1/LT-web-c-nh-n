package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.UserDao;
import DAO.UserDaoImpl;

@WebServlet(urlPatterns = "/changepass")
public class ChangePasswController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String new_pass = req.getParameter("new_passw");
		HttpSession ss = req.getSession();
		String email = (String) ss.getAttribute("otp-email");
		PrintWriter out = resp.getWriter();
		
		UserDao user = new UserDaoImpl();
		if (user.changePassword(new_pass, email)) {
			out.print("Da doi mat khau thanh cong cho " + email);
			return;
		}
		out.print("Doi mat khau that bai!");
	}
}
