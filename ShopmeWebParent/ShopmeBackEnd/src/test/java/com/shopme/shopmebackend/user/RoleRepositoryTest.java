package com.shopme.shopmebackend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.shopmecommon.entity.Role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateFristRole(){
        Role roleAdmin = new Role("Admin", "manage everything");
        Role savedRole = roleRepository.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testCreateRestRoles(){
        Role roleSalesperson = new Role("Salesperson", "manage product price, customers, shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories, brands, product, articles and menus");
        Role roleShipper = new Role("Shipper", "view products, view orders");
        Role roleAssistant = new Role("Assistant", "manage questions and reviews");
        roleRepository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
    }
}