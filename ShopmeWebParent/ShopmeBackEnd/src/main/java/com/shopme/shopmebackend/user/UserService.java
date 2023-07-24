package com.shopme.shopmebackend.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.shopmecommon.entity.Role;
import com.shopme.shopmecommon.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> listUsers() {
		return (List<User>) userRepository.findAll();
	}

	public List<Role> listRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	public void save(User user) {
		encodePassword(user);
		userRepository.save(user);
	}
	
	private void encodePassword(User user) {
		String encode = passwordEncoder.encode(user.getPassword());
		user.setPassword(encode);
	}
	public boolean isEmailUnique(String email) {
		User userByEmail = userRepository.getUserByEmail(email);
		return userByEmail == null;
	}
}
