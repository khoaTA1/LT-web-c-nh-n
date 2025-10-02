package khoa.controller;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import khoa.Constant.DirectoryPath;
import khoa.entity.User;
import khoa.repo.UserRepo;
import khoa.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	private UserRepo userRepo;
	
	@Autowired
	UserService userserv;

    UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

	// login
	@GetMapping("general/login")
	public String login(ModelMap model) {
		return "general/login";
	}

	@PostMapping("general/login")
	public String login(HttpServletRequest req, ModelMap model,
			@RequestParam(name = "username", required = true) String uname,
			@RequestParam(name = "password", required = true) String passw) {
		if (uname.isEmpty() || passw.isEmpty()) {
			model.addAttribute("msg", "Tên đăng nhập hoặc mật khẩu không thể để trống.");
			return "general/login";
		}

		User user = userserv.findByUserNameContaining(uname).get(0);

		System.out.printf("username: %s, pass: %s \n", user.getUserName(), user.getPassWord());

		if (user == null || !user.getPassWord().equals(passw)) {
			model.addAttribute("msg", "Sai tên đăng nhập hoặc mật khẩu.");
			return "general/login";
		}

		HttpSession ss = req.getSession();
		ss.setAttribute("account", user);

	
		if (user.getRoleid() == 1) {
			return "user/profile";
		} else {
			return "admin/home";
		}
	}

	// register + validation
	@GetMapping("general/register")
	public String register(ModelMap model) {
		model.addAttribute("user", new User());
		return "general/register";
	}

	@PostMapping("general/register")
	public String register(ModelMap model, 
			@Valid @ModelAttribute("user") User newUser,
			BindingResult errors,
			@RequestParam(name = "userAvatar", required = false) MultipartFile file) {

		if (errors.hasErrors()) {
			model.addAttribute("msg", "Đã xảy ra lỗi nhập liệu!");
			model.addAttribute("user", newUser);
			return "general/register";
		}
		
		// mặc định user được đăng ký là user thường (role id = 1)
		newUser.setRoleid(1);

		if (file != null && !file.isEmpty()) {
			/*
			model.addAttribute("msg", "Vui lòng chọn một file ảnh!");
			return "general/register";*/
			try {
				String originalFileName = file.getOriginalFilename();

				int index = originalFileName.lastIndexOf(".");

				String ext = originalFileName.substring(index + 1);
				String fileName = System.currentTimeMillis() + "." + ext;

				String filePath = DirectoryPath.dir + "\\userAvatars\\" + fileName;
				file.transferTo(new File(filePath));
				newUser.setAvatar("userAvatars/" + fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		/*
		} else {
			*/
		}

		if (userserv.existsByEmail(newUser.getEmail())) {
			model.addAttribute("msg", "Email đã tồn tại!");
			return "general/register";
		}

		if (userserv.existsByUserName(newUser.getUserName())) {
			model.addAttribute("msg", "Tên đăng nhập đã tồn tại!");
			return "general/register";
		}

		User checkSavedUser = userserv.save(newUser);

		if (checkSavedUser.getId() == 0) {
			model.addAttribute("msg", "Lỗi hệ thống!");
			return "general/register";
		}

		return "general/login";
	}

	// edit
	@GetMapping("general/edituser")
	public String editUser(ModelMap model) {
		return "general/edituser";
	}

	@PostMapping("general/edituser")
	public String editUser(HttpServletRequest req, ModelMap model, @RequestParam(name = "fname") String fname,
			@RequestParam(name = "phone") String phone, @RequestParam(name = "avatar") MultipartFile file) {

		HttpSession ss = req.getSession();
		User user = (User) ss.getAttribute("account");

		// System.out.printf("id: %d \n", user.getId());
		Optional<User> tempUser = userserv.findById(user.getId());
		if (tempUser.isPresent())
			user = tempUser.get();
		
		// debug
		System.out.printf("username tempuser: %s \n", user.getUserName());
		
		user.setFullName(fname);
		user.setPhone(phone);
		
		try {
			if (file.getSize() > 0) {
				// Xóa ảnh cũ
				if (user.getAvatar() != null) {
					final String oldAvaName = user.getAvatar();
					
					File oldAva = new File(DirectoryPath.dir + "\\" + oldAvaName);
					
					if (oldAva.exists()) {
						oldAva.delete();
					}
				}
				
				// Lưu ảnh mới
				String originalFileName = file.getOriginalFilename();

				int index = originalFileName.lastIndexOf(".");

				String ext = originalFileName.substring(index + 1);
				String fileName = System.currentTimeMillis() + "." + ext;

				String filePath = DirectoryPath.dir + "\\userAvatars\\" + fileName;
				file.transferTo(new File(filePath));
				user.setAvatar("userAvatars/" + fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userserv.save(user);
		ss.setAttribute("account", user);

		return "user/profile";
	}
	
	// delete
	@GetMapping("general/deleteuser")
	public String deleteUser(HttpServletRequest req, ModelMap model) {
		HttpSession ss = req.getSession();
		User user = (User) ss.getAttribute("account");
		
		if (user.getAvatar() != null) {
			final String oldAvaName = user.getAvatar();
			
			File oldAva = new File(DirectoryPath.dir + "\\" + oldAvaName);
			
			if (oldAva.exists()) {
				oldAva.delete();
			}
		}
		
		userserv.deleteById(user.getId());
		
		return "general/home";
	}
	
	// log out
	@GetMapping("general/logout")
	public String logout(HttpServletRequest req) {
		HttpSession ss = req.getSession();
		ss.invalidate();
		return "general/login";
	}
}
