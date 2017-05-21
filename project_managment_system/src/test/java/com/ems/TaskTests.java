package com.ems;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectJDBC;
import com.ems.projectsinfo.Task;
import com.ems.teaminfo.Team;
import com.ems.teaminfo.TeamJDBC;
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

import java.util.Date;

/**
 * Created by MARTYNAS on 17/05/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTests extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserJDBC sqlUser;
    @Autowired
    private ProjectJDBC sqlProject;
    @Autowired
    private TeamJDBC sqlTeam;

    private Project project;
    private User user;
    private Team team;
    private Task task;

    @Before
    public void setUp() throws Exception {
        user = new User("Test", "Password123@test.com", "test@test.com", "Name", "Last name");
        user.setId(1);
        project = new Project("Projektas","Aprašymas", new Date(System.currentTimeMillis()));
        team = new Team("Komanda","Komandos aprašymas");
        team.setId(1);
        sqlProject.create(project);
        task = new Task("Aprasymas", new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), sqlProject.getProject("Projektas").getId());
    }

    @Test
    public void createNewTask(){
        sqlProject.create(task);
        Assert.assertEquals("Aprasymas",sqlProject.getTask("Aprasymas").getDescription());
    }
}
