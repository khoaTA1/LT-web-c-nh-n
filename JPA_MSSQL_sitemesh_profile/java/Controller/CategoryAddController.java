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

@WebServlet(urlPatterns = "/categoryadd")
public class CategoryAddController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		User role = (User) ss.getAttribute("account");
		
		if (role.getRoleid() == 3) {
			req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
		} else if (role.getRoleid() == 2) {
			req.getRequestDispatcher("/views/manager/add-category.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Category category = new Category();
		DiskFileItemFactory diskFileItemFactory = DiskFileItemFactory.builder().get();
		JakartaServletFileUpload servletFileUpload = new JakartaServletFileUpload(diskFileItemFactory);
		HttpSession ss = req.getSession();
		
		User uid = (User) ss.getAttribute("account");
		
		try {
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			req.setCharacterEncoding("UTF-8");
			List<FileItem> items = servletFileUpload.parseRequest(req);
			for (FileItem item : items) {
				if (item.getFieldName().equals("name")) {
					category.setCategoryName(item.getString(StandardCharsets.UTF_8));
				} else if (item.getFieldName().equals("icon") && !item.getName().isEmpty()) {
					String originalFileName = item.getName();
					int index = originalFileName.lastIndexOf(".");
					String ext = originalFileName.substring(index + 1);
					String fileName = System.currentTimeMillis() + "." + ext;
					File file = new File(DirectoryPath.dir + "\\categoryIcons\\" + fileName);
					
					// thay đổi tham số truyền vào (giờ là đường dẫn) khi chuyển sang sử dụng commons.fileupload2.jakarta 
					item.write(Paths.get(file.getAbsolutePath()));
					category.setImages("categoryIcons/" + fileName);
				}
			}

			category.setUid(uid.getId());
			categoryService.insert(category);
			
			int roleid = uid.getRoleid();
			if (roleid == 3) {
				resp.sendRedirect("/JPA/views/admin/admin-page.jsp");
			} else if (roleid == 2) {
				resp.sendRedirect("/JPA/views/manager/manager-page.jsp");
			} else {
				resp.sendRedirect("/JPA/views/user/user-page.jsp");
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
