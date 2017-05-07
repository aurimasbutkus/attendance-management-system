package com.ems.messaging;

import com.ems.userinfo.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aurimas on 2017-05-07.
 */
@Repository
public class MessageJDBC implements MessageService{

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void create(String text, Date date, Integer sender_id, Integer receiver_id) {
        String SQL = "insert into private_message (text, date, fk_account_sender, fk_account_receiver) values (?, ?, ?, ?)";
        jdbcTemplateObject.update( SQL, text, date, sender_id, receiver_id);
        System.out.println("Created Message From = " + sender_id);
    }
    @Override
    public List<Message> listAllReceivedMessages(int userId) {
        String SQL = "select * from private_message where private_message.fk_account_receiver = ? order by private_message.date desc ";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId);
    }

    @Override
    public List<Message> listAllSentMessages(int userId) {
        String SQL = "select * from private_message where private_message.fk_account_sender = ? order by private_message.date desc";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId);
    }
}
