package Controller;

import java.io.IOException;
import java.util.List;

import Entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Service.CategoryService;
import Impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/categorycontroller")
public class CategoryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CategoryService category = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rowEachPage = 10;
		int currentPage = 1;
		
		if (req.getParameter("rowEachPage") != null) {
			rowEachPage = Integer.parseInt(req.getParameter("rowEachPage"));
		}
		
		if (req.getParameter("page") != null) {
			currentPage = Integer.parseInt(req.getParameter("page"));
		}
		
		int startIndex = (currentPage - 1) * rowEachPage;
		int totalPages = (int) Math.ceil((double) category.countRecord() / rowEachPage);
		
		List<Category> list = category.getAll(rowEachPage, startIndex);
		
		req.setAttribute("catelist", list);
		req.setAttribute("page", currentPage);
		req.setAttribute("rowEachPage", rowEachPage);
		req.setAttribute("totalPages", totalPages);
		req.getRequestDispatcher("/views/admin/list-categories.jsp").forward(req, resp);
	}
}
