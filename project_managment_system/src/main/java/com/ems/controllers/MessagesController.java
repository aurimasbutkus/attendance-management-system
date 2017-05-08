package com.ems.controllers;

import com.ems.messaging.Message;
import com.ems.messaging.MessageService;
import com.ems.userinfo.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Aurimas on 2017-05-07.
 */
@Controller
public class MessagesController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value="messages", method = RequestMethod.GET)
    public String messages(Model model, Authentication authentication){
        int userId = userDAO.getIdByUsername(authentication.getName());
        List<Message> messages = messageService.listAllReceivedMessages(userId);
        for (Message message : messages) {
            message.getFormattedText(userDAO);
        }
        model.addAttribute("messages", messages);
        return "messages";
    }

    @RequestMapping(value="messages/sent", method = RequestMethod.GET)
    public String messagesSent(Model model, Authentication authentication){
        int userId = userDAO.getIdByUsername(authentication.getName());
        List<Message> messages = messageService.listAllSentMessages(userId);
        for(Message message : messages){
            message.getFormattedText(userDAO);
        }
        model.addAttribute("messages", messages);
        return "messages/sent";
    }

    @GetMapping(value="new-message")
    public String getNewMessage(Model model){
        model.addAttribute("newMessage", new Message());
        return "new-message";
    }

    @PostMapping(value="new-message")
    public String postNewMessage(@ModelAttribute("newMessage") Message newMessage, Model model, Authentication authentication){
        newMessage.setDate(new Date(System.currentTimeMillis()));
        messageService.create(newMessage.getText(), newMessage.getDate(), userDAO.getIdByUsername(authentication.getName()), userDAO.getIdByUsername(newMessage.getReceiver_username()));
        return "messages";
    }
}
