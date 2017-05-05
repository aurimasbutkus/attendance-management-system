package com.ems.userinfo;

/**
 * Created by Marius on 2017-04-08.
 */

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(null);
        user.setUsername(rs.getString("username"));
        user.setEmail_address(rs.getString("email_address"));
        user.setPassword(rs.getString("password"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        return user;
    }
}
