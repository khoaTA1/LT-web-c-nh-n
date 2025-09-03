package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import Constant.DirectoryPath;
import Model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import sv_impl.CategoryServiceImpl;
import Constant.DirectoryPath;

@WebServlet(urlPatterns = "/categoryedit")
public class CategoryEditController extends HttpServlet {
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Category category = categoryService.get(Integer.parseInt(id));
		
		req.setAttribute("category", category);
		req.getRequestDispatcher("/wp_t1/admin/edit-category.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = new Category();
		DiskFileItemFactory diskFileItemFactory = DiskFileItemFactory.builder().get();
		JakartaServletFileUpload servletFileUpload = new JakartaServletFileUpload(diskFileItemFactory);
		
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("id")) {
					category.setCateid(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("name")) {
					category.setCatename(item.getString(StandardCharsets.UTF_8));
				} else if (item.getFieldName().equals("icon")) {
					if (item.getSize() > 0) {
						String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);
						String fileName = System.currentTimeMillis() + "." + ext;
						File file = new File(DirectoryPath.dir + fileName);
						
						item.write(Paths.get(file.getAbsolutePath()));
						category.setIcon("categoryIcons/" + fileName);
					} else {
						category.setIcon(null);
					}
				}
			}
			categoryService.edit(category);
			resp.sendRedirect(req.getContextPath() + "/categorycontroller");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
