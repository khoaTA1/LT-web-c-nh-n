package PKG.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import PKG.constant.DirectoryPath;
import PKG.entity.Category;
import PKG.service.CategoryService;
import PKG.response.response;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryCtrlAPI {
	@Autowired
	private CategoryService categoryService;

	@PostMapping(path = "getAllCategory")
	public ResponseEntity<?> getAllCategory(@RequestParam(name = "pages", defaultValue = "1") int pages,
			@RequestParam(name = "rowEachPage") int rowEachPage) {

		Pageable pageable = PageRequest.of(pages - 1, rowEachPage);
		Page<Category> catepage;

		catepage = categoryService.findAll(pageable);

		Map<String, Object> resp = new HashMap<>();
		resp.put("categories", catepage.getContent());
		resp.put("rowEachPage", rowEachPage);
		resp.put("pages", pages);
		resp.put("totalPages", catepage.getTotalPages());

		return ResponseEntity.ok(resp);
		// return new ResponseEntity<Response>(new Response(true, "Thành
		// công",categoryService.findAll()), HttpStatus.OK);
	}

	@PostMapping(path = "getCategory")
	public ResponseEntity<?> getCategory(@Validated @RequestParam("id") Integer id) {
		Optional<Category> category = categoryService.findById(id);
		if (category.isPresent()) {
			// return ResponseEntity.ok().body(category.get());
			return new ResponseEntity<response>(new response(true, "Thành công", category.get()), HttpStatus.OK);
		} else {
			// return ResponseEntity.notFound().build();
			return new ResponseEntity<response>(new response(false, "Thất bại", null), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "addCategory")
	public ResponseEntity<?> addCategory(@Validated @RequestParam("categoryName") String categoryName,
			@Validated @RequestParam("icon") MultipartFile file) {
		Optional<Category> optCategory = categoryService.findByCategoryName(categoryName);

		try {
			if (optCategory.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category đã tồn tại trong hệ thống");
				// return new ResponseEntity<Response>(new Response(false, "Loại sản phẩm này đã
				// tồn tại trong hệ thống", optCategory.get()), HttpStatus.BAD_REQUEST);
			} else {
				Category category = new Category();
				// kiểm tra tồn tại file, lưu file

				if (!file.isEmpty()) {
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();

					String originalFileName = file.getOriginalFilename();
					int index = originalFileName.lastIndexOf(".");
					String ext = originalFileName.substring(index + 1);
					String fileName = uuString + "." + ext;

					// lưu file vào trường Images
					// category.setIcon(storageService.getSorageFilename(icon, uuString));

					String filePath = DirectoryPath.dir + "\\categoryIcons\\" + fileName;
					file.transferTo(new File(filePath));
					category.setImages("categoryIcons/" + fileName);
					// storageService.store(icon, category.getIcon());
				}
				category.setCategoryName(categoryName);

				categoryService.save(category);
				// return ResponseEntity.ok().body(category);
				return new ResponseEntity<response>(new response(true, "Thêm Thành công", category), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<response>(new response(false, "Thêm thất bại", null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping(path = "updateCategory")
	public ResponseEntity<?> updateCategory(@Validated @RequestParam("categoryId") int categoryId,
			@Validated @RequestParam("categoryName") String categoryName,
			@Validated @RequestParam("icon") MultipartFile file) {

		Optional<Category> optCategory = categoryService.findById(categoryId);

		try {
			if (optCategory.isEmpty()) {
				return new ResponseEntity<response>(new response(false, "Không tìm thấy Category", null),
						HttpStatus.BAD_REQUEST);
			} else if (optCategory.isPresent()) {
				Category cate = optCategory.get();
				// kiểm tra tồn tại file, lưu file
				if (!file.isEmpty()) {
					// Xóa ảnh cũ
					if (cate.getImages() != null) {
						final String oldIconName = cate.getImages();
						
						File oldIcon = new File(DirectoryPath.dir + "\\" + oldIconName);
						
						if (oldIcon.exists()) {
							oldIcon.delete();
						}
					}
					
					// Lưu ảnh mới
					UUID uuid = UUID.randomUUID();
					String uuString = uuid.toString();

					String originalFileName = file.getOriginalFilename();
					int index = originalFileName.lastIndexOf(".");
					String ext = originalFileName.substring(index + 1);
					String fileName = uuString + "." + ext;

					// lưu file vào trường Images
					// category.setIcon(storageService.getSorageFilename(icon, uuString));
					String filePath = DirectoryPath.dir + "\\categoryIcons\\" + fileName;
					file.transferTo(new File(filePath));
					cate.setImages("categoryIcons/" + fileName);
				}

				cate.setCategoryName(categoryName);
				categoryService.save(cate);
				// return ResponseEntity.ok().body(category);
				return new ResponseEntity<response>(new response(true, "Cập nhật Thành công", cate),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@DeleteMapping(path = "deleteCategory")
	public ResponseEntity<?> deleteCategory(@Validated @RequestParam("categoryId") int categoryId) {
		Optional<Category> optCategory = categoryService.findById(categoryId);
		if (optCategory.isEmpty()) {
			return new ResponseEntity<response>(new response(false, "Không tìm thấy Category", null),
					HttpStatus.BAD_REQUEST);
		} else if (optCategory.isPresent()) {
			categoryService.deleteById(optCategory.get().getId());
			return ResponseEntity.ok().body(optCategory.get());
			// return new ResponseEntity<Response>(new Response(true, "Xóa Thành công",
			// optCategory.get()), HttpStatus.OK);
		}
		return new ResponseEntity<response>(new response(false, "Thêm thất bại", null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}