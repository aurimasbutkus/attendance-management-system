package com.ems;

import com.ems.userinfo.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private UserJDBC sql;

	private User user;
	@Before
	public void setUp() throws Exception {
		user = new User("Test", "Password123@test.com", "test@test.com", "Name", "Last name");
	}
	@Test
	public void createUser_withUserClass(){
		sql.create(user);
		Assert.assertTrue(sql.userExists(user));
	}
	@Test
	public void createUser_withUserName(){
		sql.create("Test1", "Password123", "test@test.com", "Name", "Last name");
		Assert.assertTrue(sql.userExists("Test1"));
	}
	@Test
	public void deleteUser() {
		sql.create("Test2", "Password123", "test@test.com", "Name", "Last name");
		boolean exists = sql.userExists("Test2");
		sql.delete("Test2");
		if(sql.userExists("Test2"))
			exists = true;
		else
			exists = false;
		Assert.assertFalse(exists);
	}
	@Test
	public void getUser_Valid(){
		sql.create("Test3", "Password123", "test@test.com", "Name", "Last name");
		Assert.assertEquals(sql.getUser("Test3").getUsername(), "Test3");
	}
	@Test
	public void getUser_Null() {
		User testUser = sql.getUser("badName");
		Assert.assertNull(testUser);
	}
	@Test
	public void getUserList() {
		boolean success = false;
		sql.create("List1", "Password123", "test@test.com", "Name", "Last name");
		sql.create("List3", "Password123", "test@test.com", "Name", "Last name");
		sql.create("List2", "Password123", "test@test.com", "Name", "Last name");
		List<String> names = new ArrayList<>();
		List<User> userArrayList = sql.getUserList();
		for (User user : userArrayList) {
			names.add(user.getUsername());
		}
		if(names.contains("List1") && names.contains("List2") && names.contains("List3"))
			success = true;
		Assert.assertTrue(success);
	}
}
