package com.shopme.shopmebackend.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shopme.shopmecommon.entity.User;

public interface UserRepository extends CrudRepository<User,Integer>  {
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
}
