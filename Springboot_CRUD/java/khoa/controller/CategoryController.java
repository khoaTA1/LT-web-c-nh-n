package khoa.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import khoa.Constant.DirectoryPath;
import khoa.entity.Category;
import khoa.entity.User;
import khoa.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
	@Autowired
	CategoryService cateserv;

	// Thêm
	@GetMapping("add")
	public String add(ModelMap model) {
		return "admin/category/add";
	}

	@PostMapping("add")
	public String add(HttpServletRequest req, ModelMap model, @RequestParam(name = "catename") String catename,
			@RequestParam(name = "icon") MultipartFile file, @RequestParam(name = "uid") int uid) {
		HttpSession ss = req.getSession();
		User user = (User) ss.getAttribute("account");

		Category cate = new Category();
		cate.setCategoryName(catename);
		//cate.setUid(user.getId());

		if (file == null || file.isEmpty()) {
			model.addAttribute("msg", "Vui lòng chọn một file ảnh!");
			return "admin/category/add";
		} else {
			try {
				String originalFileName = file.getOriginalFilename();

				int index = originalFileName.lastIndexOf(".");

				String ext = originalFileName.substring(index + 1);
				String fileName = System.currentTimeMillis() + "." + ext;

				String filePath = DirectoryPath.dir + "\\categoryIcons\\" + fileName;
				file.transferTo(new File(filePath));
				cate.setImages("categoryIcons/" + fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "admin/category/add";
	}

	// đọc
	@RequestMapping("list")
	public String list(HttpServletRequest req, ModelMap model, @RequestParam(name = "rowEachPage") String rowEachPage,
			@RequestParam(name = "")) {
		HttpSession ss = req.getSession();
		
		// gọi hàm findAll() trong services
		List<Category> list = cateserv.findAll();
		

		// chuyển dữ liệu từ list lên biến categories
		model.addAttribute("categories", list);
		return "admin/category/list";
	}

	// xóa
	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model, @PathVariable("categoryId") int categoryId) {
		cateserv.deleteById(categoryId);
		model.addAttribute("message", "Category is deleted!!!!");
		return new ModelAndView("redirect:/admin/category/searchpaginated", model);
	}

	// sửa
	@GetMapping("edit/{id}")
	public String editcategory(ModelMap model, @PathVariable("id") int id) {
		Category cate = cateserv.findById(id).orElse(null);
		
		if (cate == null) {
			model.addAttribute("msg", "Category không tồn tại!");
			return "admin/category/list";
		} else {
			
		}
		
		return "admin/category/edit";
	}
	
	@PostMapping("edit")
	public String editcategory(ModelMap model, @RequestParam(name = "catename") String catename,
		@RequestParam(name = "icon") MultipartFile file) {
		
		Category cate = new Category();
		
		cate.setCategoryName(catename);
		
		try {
			if (file.getSize() > 0) {
				// Xóa ảnh cũ
				if (cate.getImages() != null) {
					final String oldIconName = cate.getImages();
					
					File oldIcon = new File(DirectoryPath.dir + "\\" + oldIconName);
					
					if (oldIcon.exists()) {
						oldIcon.delete();
					}
				}
				
				// Lưu ảnh mới
				String originalFileName = file.getOriginalFilename();

				int index = originalFileName.lastIndexOf(".");

				String ext = originalFileName.substring(index + 1);
				String fileName = System.currentTimeMillis() + "." + ext;

				String filePath = DirectoryPath.dir + "\\categoryIcons\\" + fileName;
				file.transferTo(new File(filePath));
				cate.setImages("categoryIcons/" + fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "admin/category/list";
	}
	
	// tìm kiếm
	@RequestMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
		List<Category> list = null;

		// có nội dung truyền về không, name là tùy chọn khi required=false
		if (StringUtils.hasText(name)) {
			list = cateserv.findByCategoryNameContaining(name);
		} else {
			list = cateserv.findAll();
		}

		model.addAttribute("categories", list);
		return "admin/category/search";
	}

	/*
	@PostMapping("saveOrUpdate")
	public String saveupdate(ModelMap model, @RequestParam(name = "categoryName", required = true) String name,
			@RequestParam(name = "icon") MultipartFile file) {
		Category category = new Category();
		category.setCategoryName(name);

		if (file.isEmpty()) {
			model.addAttribute("msg", "Vui lòng chọn một file ảnh!");
		} else {
			try {
				String originalFileName = file.getOriginalFilename();

				int index = originalFileName.lastIndexOf(".");

				String ext = originalFileName.substring(index + 1);
				String fileName = System.currentTimeMillis() + "." + ext;

				String filePath = DirectoryPath.dir + "//" + fileName;
				file.transferTo(new File(filePath));
				category.setImages("categoryIcons/" + fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		cateserv.save(category);

		return "admin/home";
	}*/
}
