package com.ems.messaging;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aurimas on 2017-05-07.
 */
public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt("id"));
        message.setText(rs.getString("text"));
        message.setDate(rs.getDate("date"));
        message.setFkAccountSender(rs.getInt("fk_account_sender"));
        message.setFkAccountReceiver(rs.getInt("fk_account_receiver"));

        return message;
    }
}
