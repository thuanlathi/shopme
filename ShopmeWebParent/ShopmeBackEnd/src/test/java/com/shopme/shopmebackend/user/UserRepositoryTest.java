package com.shopme.shopmebackend.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.shopmecommon.entity.Role;
import com.shopme.shopmecommon.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		User userRoseMary = new User("KJane@gmail.com", "12345678K", "Mark", "L Sha");
		Role roleAdmin = testEntityManager.find(Role.class, 1); 
		userRoseMary.addRole(roleAdmin);
		User savedUser = userRepository.save(userRoseMary);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userKevinL = new User("JadeCK@hotmail.com", "12345678L", "Jace", "CK");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userKevinL.addRole(roleEditor);
		userKevinL.addRole(roleAssistant);
		User savedUser = userRepository.save(userKevinL);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = userRepository.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	@Test
	public void testgetUserById() {
		Optional<User> user01 = userRepository.findById(4);
		System.out.println(user01);
		assertThat(user01).isNotNull();
	}
	@Test
	public void testUpdateUserDetail() {
		User userJessica = userRepository.findById(12).get();
		userJessica.setEmail("Beth@gmail.com");
		userJessica.setEnabled(true);
		userRepository.save(userJessica);
	}
	@Test
	public void testUpdateUserRole() {
		User userJessica = userRepository.findById(4).get();
		Role roleSalesPerson = new Role(2);
		userJessica.addRole(roleSalesPerson);
		Role roleAdmin = new Role(1);
		userJessica.getRole().remove(roleAdmin);
	}
	@Test
	public void testDeleteUserById() {
		userRepository.deleteById(11);
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "Mark@gmail.com";
		User user = userRepository.getUserByEmail(email);
		assertThat(user).isNotNull();
	}
}
