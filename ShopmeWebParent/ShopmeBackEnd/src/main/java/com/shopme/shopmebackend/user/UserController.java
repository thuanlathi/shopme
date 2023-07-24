package com.shopme.shopmebackend.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.shopmecommon.entity.Role;
import com.shopme.shopmecommon.entity.User;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.listUsers();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}

	@GetMapping("/users/new")
	public String newUser(Model model) {
		User user = new User();
		user.setEnabled(true);
		model.addAttribute(user);
		
		
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("listRoles",listRoles);
		
		return "users-form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		userService.save(user);
		redirectAttributes.addFlashAttribute("message", "User has been created successful");
		return "redirect:/users";
	}

}
