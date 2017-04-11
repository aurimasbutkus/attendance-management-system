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
        user.setUser_id(null);
        user.setUsername(rs.getString("username"));
        user.setEmail_address(rs.getString("email_address"));
        user.setPassword(rs.getString("password"));
        user.setUser_role(rs.getInt("user_role"));
        user.setFk_Employee(rs.getInt("fk_Employee"));

        return user;
    }
}
