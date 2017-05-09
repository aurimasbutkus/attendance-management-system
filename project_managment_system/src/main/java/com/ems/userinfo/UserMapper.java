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
        user.setEmailAddress(rs.getString("email_address"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setGender(rs.getInt("gender"));
        user.setNationality(rs.getString("nationality"));
        user.setDateOfBirth(rs.getDate("date_of_birth"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setWorkStatus(rs.getInt("work_status"));
        user.setEnabled(rs.getInt("enabled"));
        user.setFkTeam(rs.getInt("fk_Team"));
        return user;
    }
}
