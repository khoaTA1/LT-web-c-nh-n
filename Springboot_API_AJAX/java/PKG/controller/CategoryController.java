package PKG.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import PKG.constant.DirectoryPath;
import PKG.entity.Category;
import PKG.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	CategoryService cateserv;

	// Thêm
	@GetMapping("add")
	public String add(ModelMap model) {
		model.addAttribute("category", new Category());
		return "views/add-category";
	}

	@PostMapping("add")
	public String add(HttpServletRequest req, ModelMap model, @ModelAttribute("category") Category cate,
			@RequestParam(name = "icon") MultipartFile file) {
		
		if (file == null || file.isEmpty()) {
			model.addAttribute("msg", "Vui lòng chọn một file ảnh!");
			model.addAttribute("category", cate);
			return "views/add-category";
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
		
		cateserv.save(cate);

		return "views/admin-home";
	}

	// sửa
		@GetMapping("edit/{id}")
		public String editcategory(ModelMap model, @PathVariable("id") int id) {
			Category cate = cateserv.findById(id).orElse(null);
			
			if (cate == null) {
				model.addAttribute("msg", "Category không tồn tại!");
				model.addAttribute("category", cate);
				return "views/list-category";
			} else {
				model.addAttribute("category", cate);
			}
			
			return "views/edit-category";
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
			
			return "views/ajax/edit";
		}
		
	// đọc
	@RequestMapping("list")
	public String list(HttpServletRequest req, ModelMap model, @RequestParam(name = "rowEachPage", defaultValue = "10") int rowEachPage,
			@RequestParam(name = "pages", defaultValue = "1") int pages) {
		Pageable pageable = PageRequest.of(pages-1, rowEachPage);
		Page<Category> catepage;
		
		catepage = cateserv.findAll(pageable);
		
		// chuyển dữ liệu từ list lên biến categories
		model.addAttribute("categories", catepage.getContent());
		model.addAttribute("rowEachPage", rowEachPage);
		model.addAttribute("pages", pages);
		model.addAttribute("totalPages", catepage.getTotalPages());
		
		return "views/list-category";
	}

	// xóa
	@GetMapping("delete/{categoryId}")
	public String delete(ModelMap model, @PathVariable("categoryId") int categoryId) {
		cateserv.deleteById(categoryId);
		model.addAttribute("msg", "Category is deleted!!!!");
		return "redirect:/category/list";
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
		return "views/search";
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
