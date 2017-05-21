package com.ems.messaging;

import com.ems.userinfo.User;
import com.ems.userinfo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void createTeamMessage(String text, Date date, Integer sender_id, Integer receiving_team_id) {
        String SQL = "insert into team_message (text, date, fk_account_sender, fk_team_receiver) values (?, ?, ?, ?)";
        jdbcTemplateObject.update(SQL, text, date, sender_id, receiving_team_id);
    }

    @Override
    public void create(Message message) {
        String SQL = "insert into private_message (text, date, fk_account_sender, fk_account_receiver) values (?, ?, ?, ?)";
        jdbcTemplateObject.update( SQL, message.getText(), message.getDate(), message.getFkAccountSender(), message.getFkAccountReceiver());
    }
    @Override
    public List<Message> listAllReceivedMessages(Integer userId) {
        String SQL = "select * from private_message where private_message.fk_account_receiver = ? order by private_message.id DESC";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId);
    }

    @Override
    public List<Message> listAllSentMessages(Integer userId) {
        String SQL = "select * from private_message where private_message.fk_account_sender = ? order by private_message.id DESC";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId);
    }

    @Override
    public List<User> listAllInteractedUsers(Integer userId) {
        String SQL = "select distinct account.* from private_message, account where private_message.fk_account_sender = ? OR private_message.fk_account_receiver = ? order by private_message.id desc";
        return jdbcTemplateObject.query(SQL, new UserMapper(), userId, userId);
    }

    @Override
    public List<Message> listAllMessagesWith(Integer userId, Integer receiverId) {
        String SQL = "select distinct private_message.id, private_message.text, private_message.date, private_message.fk_account_sender, private_message.fk_account_receiver from private_message, account where (private_message.fk_account_receiver = ? AND private_message.fk_account_sender = ?) OR (private_message.fk_account_receiver = ? AND private_message.fk_account_sender = ?) order by id asc";
        return jdbcTemplateObject.query(SQL, new MessageMapper(), userId, receiverId, receiverId, userId);
    }
    @Override
    public List<Message> listAllMessages() {
        String SQL = "select * from private_message";
        return jdbcTemplateObject.query(SQL, new MessageMapper());
    }
    @Override
    public Message getMessage(Integer id) {
        String SQL = "select * from private_message where id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new MessageMapper());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void updateEverything(Message message){
        String SQL = "update private_message set text = ?, date= ?, fk_account_sender = ?," +
                " fk_account_receiver = ? where id = ?";
        jdbcTemplateObject.update(SQL, message.getText(), message.getDate(), message.getFkAccountSender(),
                message.getFkAccountReceiver(), message.getId());
        System.out.println("Updated message info with id: " + message.getId() );
    }
    @Override
    public void delete(int id) {
        String SQL = "delete from private_message where id = ?";
        jdbcTemplateObject.update(SQL, id);
        System.out.println("Deleted message with id: " + id );
    }

    @Override
    public List<TeamMessage> listAllTeamMessages(Integer fkTeam) {
        String SQL = "select distinct team_message.id, team_message.text, team_message.date, team_message.fk_account_sender, team_message.fk_team_receiver from team_message, account where team_message.fk_team_receiver = ? order by id asc";
        return jdbcTemplateObject.query(SQL, new TeamMessageMapper(), fkTeam);
    }

    @Override
    public boolean messageExists(Integer id) {
        String SQL = "select * from private_message where id = ?";
        try {
             return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new MessageMapper()).getId() == id;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean messageExists(String text) {
        String SQL = "select * from private_message where text = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{text}, new MessageMapper()).getText().equals(text);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    private boolean validate(String SQL, Object o){
        try {
            jdbcTemplateObject.queryForObject(SQL, new Object[]{o}, new MessageMapper());
            return true;
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
