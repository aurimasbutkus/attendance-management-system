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

@RunWith(SpringRunner.class)
@SpringBootTest
public class userJDBCtest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private UserJDBC sql;

	private User user;
	@Before
	public void setUp() throws Exception {
		user = new User("Test", "test@test.com", "Password123");
	}
	@Test
	public void createUser_withUserClass(){
		sql.create(user);
		Assert.assertTrue(sql.userExists(user));
	}
	@Test
	public void createUser_withUserName(){
		sql.create("Test1", "test@test.com", "Password123");
		Assert.assertTrue(sql.userExists("Test1"));
	}
	@Test
	public void deleteUser() {
		sql.create("Test2", "test@test.com", "Password123");
		boolean deleted = sql.userExists("Test2");
		sql.delete("Test2");
		if(sql.userExists("Test2")) deleted = false;
		Assert.assertTrue(deleted);
	}
	@Test
	public void getUser_Valid(){
		sql.create("Test3", "test@test.com", "Password123");
		Assert.assertEquals(sql.getUser("Test3").getUsername(), "Test3");
	}
	@Test
	public void getUser_Null() {
		User testUser = sql.getUser("badName");
		Assert.assertNull(testUser);
	}
}
