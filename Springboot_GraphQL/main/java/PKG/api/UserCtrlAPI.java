package PKG.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Controller;

import PKG.DTO.userInput;
import PKG.DTO.userUpdateInput;
import PKG.entity.Category;
import PKG.entity.User;
import PKG.service.CategoryService;
import PKG.service.UserService;
import graphql.GraphqlErrorException;

@Controller
public class UserCtrlAPI {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService cateservice;

    @QueryMapping
    public User getUserById(@Argument("id") int id) {
    	return userService.findById(id).get();
    }

    @QueryMapping
    public List<User> getAllUsers() {
    	return userService.findAll();
    }

    @MutationMapping
    public User createUser(@Argument("input") userInput input) {
    	Optional<User> existingUser = userService.findByEmail(input.getEmail());
        if (existingUser.isPresent()) {
            throw GraphqlErrorException.newErrorException()
                .message("Email đã tồn tại")
                .errorClassification(ErrorType.BAD_REQUEST)
                .build();
        }
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPasswd(input.getPasswd());
        user.setPhone(input.getPhone());
        // Nếu có category thì xử lý thêm ở đây

        return userService.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument("input") userUpdateInput input) {
    	Optional<User> optional = userService.findById(input.getId());
        if (optional.isEmpty()) throw new RuntimeException("User not found");

        User user = optional.get();

        if (input.getFullName() != null) user.setFullName(input.getFullName());
        if (input.getEmail() != null) user.setEmail(input.getEmail());
        if (input.getPasswd() != null) user.setPasswd(input.getPasswd());
        if (input.getPhone() != null) user.setPhone(input.getPhone());

        return userService.save(user);
    }

    @MutationMapping
    public void deleteUser(@Argument("id") String id) {
    	userService.deleteById(Integer.parseInt(id));
    }
    
    @MutationMapping
    public User updateUserCategories(@Argument int userId, @Argument List<Integer> categoryIds) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        // Gán danh sách category từ categoryIds
        Set<Category> categories = categoryIds.stream()
        	    .map(id -> cateservice.findById(id).orElse(null))
        	    .filter(c -> c != null) // lọc bỏ null
        	    .collect(Collectors.toSet());

        user.setCategories(categories); 
        return userService.save(user);
    }
}