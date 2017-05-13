package com.ems.teaminfo;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectMapper;
import com.ems.userinfo.User;
import com.ems.userinfo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Martynas on 5/7/2017.
 */
@Repository
public class TeamJDBC implements TeamService {

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public List<User> getMembers(Integer teamId) {
        String SQL = "select account.* from team, account where account.fk_Team = team.id and team.id = ?";
        return jdbcTemplateObject.query(SQL, new Object[]{teamId}, new UserMapper());
    }

    @Override
    public Team getTeamByUser(Integer id) {
        String SQL = "select team.* from team, account where account.fk_Team = team.id and account.id = ?";
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TeamMapper());
    }

    @Override
    public void addMemberToTeam(Integer team_id, Integer member_id)
    {
        String SQL = "update account set account.fk_Team = ? where account.id = ?";
        jdbcTemplateObject.update(SQL, team_id, member_id);
        System.out.println("Added user with id " + member_id + " to team with id " + team_id);
    }

    @Override
    public Team getTeamById(Integer id) {
        String SQL = "select team.* from team where team.id = ?";
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TeamMapper());
    }

    @Override
    public List<Project> getProjects(Integer id) {
        String SQL = "select project.* from team, project, project_teams where team.id = ? and project_teams.fk_Team = team.id and project_teams.fk_Project = project.id";
        return jdbcTemplateObject.query(SQL, new Object[]{id}, new ProjectMapper());
    }
}
