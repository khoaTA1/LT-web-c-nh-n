package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import sv_impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/categorycontroller")
public class CategoryController extends HttpServlet {
	CategoryService category = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> list = category.getAll();
		
		req.setAttribute("catelist", list);
		req.getRequestDispatcher("/wp_t1/admin/list-categories.jsp").forward(req, resp);
	}
}
