package com.shopme.shopmebackend.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void testPasswordEncoder() {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		String rawPassword = "yearOf2023";
		String encodedPassword = bCrypt.encode(rawPassword);
		System.out.println(encodedPassword);
		boolean matches = bCrypt.matches(rawPassword, encodedPassword);
		assertThat(matches).isTrue();
	}

}
