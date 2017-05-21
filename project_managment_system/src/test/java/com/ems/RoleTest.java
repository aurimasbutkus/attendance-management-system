package com.ems;

import com.ems.userinfo.Role;
import com.ems.userinfo.RoleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private RoleService sql;

	private Role role;
	@Before
	public void setUp() throws Exception {
		role = new Role("admin", "ADMIN");
	}
	@Test
	public void createRole_withRoleClass(){
		sql.createRole(role);
		Assert.assertTrue(sql.roleExists(role.getUsername(), role.getRole()));
	}
	@Test
	public void createRole_withRoleName(){
		sql.createRole("Test1", "TestRole");
		Assert.assertTrue(sql.roleExists("Test1", "TestRole"));
	}
	@Test
	public void deleteRole() {
		sql.createRole("Test1", "TestRole");
		boolean exists = sql.roleExists("Test1", "TestRole");
		sql.delete("Test1", "TestRole");
		if(!sql.roleExists("Test1", "TestRole"))
			exists = false;
		Assert.assertFalse(exists);
	}

	@Test
	public void getRole_Null() {
		Role testRole = sql.getRole("badName", "roleless");
		Assert.assertNull(testRole);
	}
	@Test
	public void getRoleList() {
		boolean success = false;
		sql.createRole("admin", "USER");
		sql.createRole("admin", "MANAGER");
		sql.createRole("admin", "ADMIN");
		List<String> roles = sql.getRoles("admin");
		if(roles.contains("USER") && roles.contains("MANAGER") && roles.contains("ADMIN"))
			success = true;
		Assert.assertTrue(success);
	}
	@Test
	public void updateEverythingRole() {
		boolean success = false;
		Integer id;
		String username, userRole, newUsername, newUserRole;
		sql.createRole("admin", "USER");
		Role role = sql.getRole("admin", "USER");
		id = role.getId();
		username =  role.getUsername();
		userRole =  role.getRole();
		newUsername = "user";
		newUserRole = "ADMIN";
		sql.updateEverything(id, newUsername, newUserRole);
		role = sql.getRole(id);
		if(!Objects.equals(role.getUsername(), username) && !Objects.equals(role.getRole(), userRole))
			success = true;
		Assert.assertTrue(success);
	}
}
