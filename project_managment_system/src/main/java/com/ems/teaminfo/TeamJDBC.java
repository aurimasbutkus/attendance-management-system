package com.ems.teaminfo;

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
    public List<User> getMembers(Integer team_id) {
        String SQL = "select account.* from team, account where account.fk_Team = team.team_id and team.team_id = ?";
        return jdbcTemplateObject.query(SQL, new Object[]{team_id}, new UserMapper());
    }

    @Override
    public Team getTeamByUser(Integer id) {
        String SQL = "select team.* from team, account where account.fk_Team = team.team_id and account.id = ?";
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TeamMapper());
    }
}