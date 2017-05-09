package com.ems.teaminfo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Martynas on 5/7/2017.
 */

public class TeamMapper implements RowMapper<Team> {
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        team.setDescription(rs.getString("description"));
        return team;
        }
}
