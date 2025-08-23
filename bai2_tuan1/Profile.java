package tao_proj;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Profile")
public class Profile extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession(false);
		
		PrintWriter out = resp.getWriter();
		
		if (ss != null) {
			String usern = (String)ss.getAttribute("user");
			
			// Tìm thời gian tồn tại còn lại kể từ lần cuối truy cập session 
			int MaxIntTime = ss.getMaxInactiveInterval();
			long LastAccess = ss.getLastAccessedTime();
			long CurrentTime = System.currentTimeMillis();
			
			long TTL = MaxIntTime - (CurrentTime - LastAccess) / 1000;
			
			out.println("Chao mung den voi profile cua " + usern + "\nThoi gian con lai truoc khi phai dang nhap lai: " + TTL);
		} else {
			out.println("Vui long dang nhap lai");
			resp.sendRedirect("/tao_proj/Login2.html");
		}
	}

}
