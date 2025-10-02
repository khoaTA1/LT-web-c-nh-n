package khoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import khoa.Constant.DirectoryPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class uploadImage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GetMapping("image")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");

		File file = new File(DirectoryPath.dir + "\\" + fileName);

		/*
		String mime = getServletContext().getMimeType(file.getName());
		if (mime == null)
			mime = "application/octet-stream";
		resp.setContentType(mime);
		*/
		resp.setContentType("image/jpeg");
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		} else {
			
		}
	}
}
