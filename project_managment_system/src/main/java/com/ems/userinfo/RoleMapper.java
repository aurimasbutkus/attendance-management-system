package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setUsername(rs.getString("username"));
        role.setRole(rs.getString("role"));
        return role;
    }
}
