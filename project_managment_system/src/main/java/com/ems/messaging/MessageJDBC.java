package com.ems.messaging;

import com.ems.userinfo.User;
import com.ems.userinfo.UserMapper;
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
    }
    @Override
    public List<Message> listAllReceivedMessages(int userId) {
        String SQL = "select * from private_message where private_message.fk_account_receiver = ? order by private_message.message_id DESC";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId);
    }

    @Override
    public List<Message> listAllSentMessages(int userId) {
        String SQL = "select * from private_message where private_message.fk_account_sender = ? order by private_message.message_id DESC";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId);
    }

    @Override
    public List<User> listAllInteractedUsers(int userId) {
        String SQL = "select distinct account.* from private_message, account where private_message.fk_account_sender = ? OR private_message.fk_account_receiver = ? order by private_message.message_id desc";
        return jdbcTemplateObject.query(SQL, new UserMapper(), userId, userId);
    }

    @Override
    public List<Message> listAllMessagesWith(int userId, int receiverId) {
        String SQL = "select distinct private_message.message_id, private_message.text, private_message.date, private_message.fk_account_sender, private_message.fk_account_receiver from private_message, account where (private_message.fk_account_receiver = ? AND private_message.fk_account_sender = ?) OR (private_message.fk_account_receiver = ? AND private_message.fk_account_sender = ?) order by message_id desc";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId, receiverId, receiverId, userId);
    }
}
