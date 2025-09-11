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
import Entity.Category;
import Entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Service.CategoryService;
import Impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/categoryedit")
public class CategoryEditController extends HttpServlet {
	CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Category category = categoryService.get(Integer.parseInt(id));

		req.setAttribute("category", category);
		HttpSession ss = req.getSession();

		User uid = (User) ss.getAttribute("account");

		int roleid = uid.getRoleid();
		if (roleid == 3) {
			req.getRequestDispatcher("/views/admin/edit-category.jsp").forward(req, resp);
		} else if (roleid == 2) {
			req.getRequestDispatcher("/views/manager/edit-category.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/views/user/edit-category.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = new Category();
		DiskFileItemFactory diskFileItemFactory = DiskFileItemFactory.builder().get();
		JakartaServletFileUpload servletFileUpload = new JakartaServletFileUpload(diskFileItemFactory);

		HttpSession ss = req.getSession();

		User uid = (User) ss.getAttribute("account");

		int roleid = uid.getRoleid();
		
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("id")) {
					 String id_Str = item.getString(StandardCharsets.UTF_8);
					    if (id_Str != null && !id_Str.isEmpty()) {
					        category.setId(Integer.parseInt(id_Str));
					    }
				} else if (item.getFieldName().equals("name")) {
					category.setCategoryName(item.getString(StandardCharsets.UTF_8));
				} else if (item.getFieldName().equals("icon")) {
					if (item.getSize() > 0) {
						String originalFileName = item.getName();
						int index = originalFileName.lastIndexOf(".");
						String ext = originalFileName.substring(index + 1);
						String fileName = System.currentTimeMillis() + "." + ext;
						File file = new File(DirectoryPath.dir + "\\categoryIcons\\" + fileName);

						item.write(Paths.get(file.getAbsolutePath()));
						category.setImages("categoryIcons/" + fileName);
					} else {
						category.setImages(null);
					}
				}
			}
			category.setUid(uid.getId());
			categoryService.edit(category);

			if (roleid == 3) {
				resp.sendRedirect(req.getContextPath() + "/views/admin/admin-page.jsp");
			} else if (roleid == 2) {
				resp.sendRedirect(req.getContextPath() + "/views/manager/manager-page.jsp");
			} else {
				resp.sendRedirect(req.getContextPath() + "/views/user/user-page.jsp");
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
