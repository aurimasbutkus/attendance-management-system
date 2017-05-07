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
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail_address(rs.getString("email_address"));
        user.setPassword(rs.getString("password"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setGender(rs.getString("gender"));
        user.setNationality(rs.getString("nationality"));
        user.setPhone_number(rs.getString("date_of_birth"));
        user.setPhone_number(rs.getString("phone_number"));
        user.setWork_status(rs.getString("work_status"));
        user.setEnabled(rs.getInt("enabled"));
        user.setFk_Team(rs.getInt("fk_Team"));
        return user;
    }
}
