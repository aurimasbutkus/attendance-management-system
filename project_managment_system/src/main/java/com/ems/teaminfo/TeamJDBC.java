package com.ems.teaminfo;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectMapper;
import com.ems.userinfo.User;
import com.ems.userinfo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            String SQL = "select team.* from team, account where account.fk_Team = team.id and account.id = ?";
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TeamMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Team getTeamByName(String name) {
        try {
            String SQL = "select * from team where team.name = ?";
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, new TeamMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void addMemberToTeam(Integer team_id, Integer member_id)
    {
        String SQL = "update account set account.fk_Team = ? where account.id = ?";
        jdbcTemplateObject.update(SQL, team_id, member_id);
        System.out.println("Added user with id " + member_id + " to team with id " + team_id);
    }

    @Override
    public void removeMemberFromTeam(Integer member_id)
    {
        String SQL = "update account set account.fk_Team = null where account.id = ?";
        jdbcTemplateObject.update(SQL, member_id);
        System.out.println("Removed user with id " + member_id + " from team ");
    }

    @Override
    public Team getTeamById(Integer id) {
        try {
            String SQL = "select team.* from team where team.id = ?";
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TeamMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Project> getProjects(Integer id) {
        try {
            String SQL = "select project.* from team, project, project_teams where team.id = ? and project_teams.fk_Team = team.id and project_teams.fk_Project = project.id";
            return jdbcTemplateObject.query(SQL, new Object[]{id}, new ProjectMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void createNewTeam(Team team)
    {
        String SQL = "insert into team (name, description) VALUES( ?, ?)";
        jdbcTemplateObject.update(SQL, team.getName(), team.getDescription());
        System.out.println("Added new team: " + team.getName()+ " : " + team.getDescription());
    }

}
