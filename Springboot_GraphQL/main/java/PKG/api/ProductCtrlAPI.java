package PKG.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import PKG.entity.Category;
import PKG.entity.Product;
import PKG.service.CategoryService;
import PKG.service.ProductService;

@Controller
public class ProductCtrlAPI {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryServie;
	
	@QueryMapping
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	// Hiển thị tất cả product có price từ thấp đến cao
	@QueryMapping
	public List<Product> getProductsOrderByPriceAsc() {
		return productService.findAllByOrderByPriceAsc();
	}

	// Lấy tất cả product của 1 category
	@QueryMapping
	public List<Product> getProductsByCategoryId(@Argument int categoryId) {
		Category cate = categoryServie.findById(categoryId).get();
		return productService.findAllByCategory(cate);
	}

	@MutationMapping
	public Product createProduct(@Argument Product input) {
		return productService.save(input);
	}

	@MutationMapping
	public Product updateProduct(@Argument Long id, @Argument Product input) {
		Optional<Product> optional = productService.findById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Product not found");
		Product product = optional.get();

		product.setTitle(input.getTitle());
		product.setQuantity(input.getQuantity());
		product.setDescription(input.getDescription());
		product.setPrice(input.getPrice());
		product.setUserId(input.getUserId());
		product.setCategory(input.getCategory());

		return productService.save(product);
	}

	@MutationMapping
	public Boolean deleteProduct(@Argument Long id) {
		productService.deleteById(id);
		return true;
	}
}