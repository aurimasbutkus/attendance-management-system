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
        message.setMessage_id(null);
        message.setText(rs.getString("text"));
        message.setDate(rs.getDate("date"));
        message.setFk_account_sender(rs.getInt("fk_account_sender"));
        message.setFk_account_receiver(rs.getInt("fk_account_receiver"));

        return message;
    }
}
