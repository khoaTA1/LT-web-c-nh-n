package PKG.api;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import PKG.entity.Category;
import PKG.service.CategoryService;

@Controller
public class CategoryCtrlAPI {
	@Autowired
    private CategoryService categoryService;

    @QueryMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @QueryMapping
    public Optional<Category> getCategoryById(@Argument int id) {
        return categoryService.findById(id);
    }

    @MutationMapping
    public Category createCategory(@Argument Category input) {
        return categoryService.save(input);
    }

    @MutationMapping
    public Category updateCategory(@Argument int id, @Argument Category input) {
        Optional<Category> optional = categoryService.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Category not found");
        Category category = optional.get();

        category.setCategoryName(input.getCategoryName());
        category.setImages(input.getImages());
        category.setUsers(input.getUsers());
        category.setProducts(input.getProducts());

        return categoryService.save(category);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument int id) {
        categoryService.deleteById(id);
        return true;
    }
}