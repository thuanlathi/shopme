package com.shopme.shopmebackend.user;

import org.springframework.data.repository.CrudRepository;


import com.shopme.shopmecommon.entity.Role;


public interface RoleRepository extends CrudRepository<Role,Integer> {
}
