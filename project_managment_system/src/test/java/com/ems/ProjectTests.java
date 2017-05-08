package com.ems;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectJDBC;
import com.ems.userinfo.User;
import com.ems.userinfo.UserJDBC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

/**
 * Created by Martynas on 5/7/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTests extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserJDBC sqlUser;
    @Autowired
    private ProjectJDBC sqlProject;

    private Project project;
    private User user;
    @Before
    public void setUp() throws Exception {
        user = new User("Test", "Password123@test.com", "test@test.com", "Name", "Last name");
        project = new Project("Projektas","Aprašymas", new Date(System.currentTimeMillis()));
    }

    @Test
    public void projectExists_FalseProject(){
        sqlProject.create("Good name","D",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Assert.assertFalse(sqlProject.projectExists("baaad name"));
    }

    @Test
    public void createProject_withProjectClass(){
        sqlProject.create(project);
        Assert.assertTrue(sqlProject.projectExists(project));
    }

    @Test
    public void createProject_withProject_withStartDate(){
        sqlProject.create("Good name","D",new Date(System.currentTimeMillis()));
        Assert.assertTrue(sqlProject.projectExists("Good name"));
    }

    @Test
    public void createProject_withProject_WithEndDate(){
        sqlProject.create("PP","D",new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Assert.assertTrue(sqlProject.projectExists("PP"));
    }



}