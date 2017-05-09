package com.ems.messaging;

import com.ems.userinfo.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aurimas on 2017-05-07.
 */
public interface MessageService {

    void create(String text, Date date, Integer sender_id, Integer receiver_id);
    List<Message> listAllReceivedMessages(Integer userId);
    List<Message> listAllSentMessages(Integer userId);
    List<User> listAllInteractedUsers(Integer userId); //Used to get all users current person has sent or received messages.
    List<Message> listAllMessagesWith(Integer userId, Integer receiverId);
    List<Message> listAllMessages();
    Message getMessage(Integer id);
    void updateEverything(Message message);
}
