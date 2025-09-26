package PKG.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import PKG.DTO.userInput;
import PKG.DTO.userUpdateInput;
import PKG.entity.User;
import PKG.service.UserService;

@Controller
public class UserCtrlAPI {
	@Autowired
	private UserService userService;

    @QueryMapping
    public User getUserById(int id) {
    	return userService.findById(id).get();
    }

    @QueryMapping
    public List<User> getAllUsers() {
    	return userService.findAll();
    }

    @MutationMapping
    public User createUser(@Argument User input) {
    	return userService.save(input);
    }

    @MutationMapping
    public User updateUser(@Argument int id, @Argument User input) {
        Optional<User> optional = userService.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("User not found");
        User user = optional.get();

        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPasswd(input.getPasswd());
        user.setPhone(input.getPhone());
        user.setCategories(input.getCategories());

        return userService.save(user);
    }

    @MutationMapping
    public void deleteUser(int id) {
    	userService.deleteById(id);
    }
}