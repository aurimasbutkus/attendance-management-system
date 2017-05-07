package com.ems.messaging;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aurimas on 2017-05-07.
 */
public interface MessageService {

    void create(String text, Date date, Integer sender_id, Integer receiver_id);
    List<Message> listAllReceivedMessages(int userId);
    List<Message> listAllSentMessages(int userId);

}
