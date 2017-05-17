package com.ems.messaging;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aurimas on 2017-05-17.
 */
public class TeamMessageMapper implements RowMapper<TeamMessage>{
    @Override
    public TeamMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeamMessage message = new TeamMessage();
        message.setId(rs.getInt("id"));
        message.setText(rs.getString("text"));
        message.setDate(rs.getDate("date"));
        message.setFkAccountSender(rs.getInt("fk_account_sender"));
        message.setFkTeamReceiver(rs.getInt("fk_team_receiver"));

        return message;
    }

}
