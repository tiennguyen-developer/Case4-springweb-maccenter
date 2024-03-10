package com.codegym.maccenter.controller;

import com.codegym.maccenter.model.CartItem;
import com.codegym.maccenter.model.Role;
import com.codegym.maccenter.model.User;
import com.codegym.maccenter.service.RoleService;
import com.codegym.maccenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;


	@GetMapping("/login")
	public String loginForm() {
		CartItem.cart.clear();    // làm sạch đơn hàng lúc nhập
		return "login_template";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		return "register_template";
	}

	@PostMapping("/register")
	public String registerAdd(@ModelAttribute User user, HttpServletRequest request) throws ServletException {

//		String email = user.getEmail();
//
//		if (userService.isEmailAlreadyInUse(email)) {
//			// Email đã tồn tại, hiển thị thông báo lỗi
//			model.addAttribute("error", "Email đã tồn tại, vui lòng sử dụng email khác.");
//			return "register_form"; // Trả về trang đăng ký với thông báo lỗi
//		}



		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));

		List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.findRoleById(1).get());
		roleList.add(roleService.findRoleById(2).get());
		user.setRoles(roleList);
		userService.saveUser(user);

		request.login(user.getEmail(), password);
		return "redirect:/";
	}
}
