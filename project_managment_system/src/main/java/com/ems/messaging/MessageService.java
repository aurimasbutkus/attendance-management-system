package com.ems.messaging;

import com.ems.userinfo.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aurimas on 2017-05-07.
 */
public interface MessageService {

    void create(String text, Date date, Integer sender_id, Integer receiver_id);
    List<Message> listAllReceivedMessages(int userId);
    List<Message> listAllSentMessages(int userId);
    List<User> listAllInteractedUsers(int userId); //Used to get all users current person has sent or received messages.
    List<Message> listAllMessagesWith(int userId, int receiverId);

}
