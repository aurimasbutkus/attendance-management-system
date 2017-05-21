package com.ems;

import com.ems.messaging.Message;
import com.ems.messaging.MessageJDBC;
import com.ems.userinfo.User;
import com.ems.userinfo.UserJDBC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

/**
 * Created by Aurimas on 2017-05-21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTests extends AbstractTransactionalJUnit4SpringContextTests {
    //@Autowired
   // private UserJDBC sqlUser;
    @Autowired
    private MessageJDBC sqlMessage;
   // private User user;
    private Message message;
    private Date date;
    @Before
    public void setUp() throws Exception {
        //user = new User("Test", "Password123@test.com", "test@test.com", "Name", "Last name");
        message = new Message("Testas", 14,14);
        date = new Date(System.currentTimeMillis());
        message.setDate(date);
        message.setId(999);
    }

    @Test
    public void createMessage_withMessageObject(){
        sqlMessage.create(message);
        Assert.assertTrue(sqlMessage.messageExists("Testas"));
    }

    @Test
    public void createMessage_withRawData(){
        sqlMessage.create("Testas1", date,14,14);
        Assert.assertTrue(sqlMessage.messageExists("Testas1"));
    }

    @Test
    public void deleteMessage() {
        message.setId(998);
        sqlMessage.create(message);
        boolean deleted;
        sqlMessage.delete(998);
        if(sqlMessage.messageExists(998)) deleted = false;
        else deleted = true;
        Assert.assertTrue(deleted);
    }

    @Test
    public void getMessage_invalid(){
        Assert.assertNull(sqlMessage.getMessage(996));
    }
}
