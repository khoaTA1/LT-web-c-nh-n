package tao_proj;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RCookie")
public class ReadCookie extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String usern = "";
		
		Cookie[] cookie = req.getCookies();
		
		for (Cookie c:cookie) {
			if (c.getName().equals("username")) {
				usern = c.getValue();
			}
			if (usern.equals("")) {
				resp.sendRedirect("/Login");
			}
		}
		out.println(usern);
	}
}
