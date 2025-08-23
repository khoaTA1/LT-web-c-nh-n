package tao_proj;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login2")
public class LoginbyServlet2 extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String usern=req.getParameter("usern");
		String pass=req.getParameter("pass");
		
		PrintWriter out = resp.getWriter();
		
		if (usern.equals("Khoa") && pass.equals("123456789")) {
			out.print("Chao mung: " + usern);
			HttpSession ss = req.getSession();
			ss.setAttribute("user", usern);
			ss.setMaxInactiveInterval(10);
			
			resp.sendRedirect("/tao_proj/Profile");
		} else {
			resp.sendRedirect("/tao_proj/Login2.html");
			//req.getRequestDispatcher("/tao_proj/Login2.html").include(req, resp);
		}
	}
}
