package com.ems;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectJDBC;
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
 * Created by Martynas on 5/17/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamTests extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private TeamJDBC sqlTeam;

    private Project project;
    private User user;
    private Team team;

    @Before
    public void setUp() throws Exception {
        user = new User("Test", "Password123@test.com", "test@test.com", "Name", "Last name");
        user.setId(1);
        project = new Project("Projektas","Aprašymas", new Date(System.currentTimeMillis()));
        team = new Team("Komanda","Komandos aprašymas");
        team.setId(1);
    }

    @Test
    public void createNewTeam(){
        sqlTeam.createNewTeam(team);
        Assert.assertEquals("Komanda",sqlTeam.getTeamByName("Komanda").getName());
    }

    @Test
    public void getTeamById(){
        sqlTeam.createNewTeam(team);
        Team newTeam = sqlTeam.getTeamByName("Komanda");
        Assert.assertNotNull(sqlTeam.getTeamById(newTeam.getId()));
    }

    @Test
    public void getTeamByName(){
        sqlTeam.createNewTeam(team);
        Team newTeam = sqlTeam.getTeamByName("Komanda");
        Assert.assertEquals("Komanda", newTeam.getName());
    }

    @Test
    public void getTeamByName_BadName(){
        sqlTeam.createNewTeam(team);
        Team newTeam = sqlTeam.getTeamByName("Neratokio");
        Assert.assertNull(newTeam);
    }

    @Test
    public void getTeamById_BadId(){
        sqlTeam.createNewTeam(team);
        Team newTeam = sqlTeam.getTeamById(-55);
        Assert.assertNull(newTeam);
    }

}
