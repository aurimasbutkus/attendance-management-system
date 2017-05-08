package com.ems.controllers;

import com.ems.messaging.Message;
import com.ems.messaging.MessageService;
import com.ems.userinfo.User;
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

    @RequestMapping(value="/messages", method = RequestMethod.GET)
    public String messages(Model model, Authentication authentication){
        int userId = userDAO.getIdByUsername(authentication.getName());
        List<User> users = messageService.listAllInteractedUsers(userId);
        model.addAttribute("users", users);
        return "messages";
    }

    @GetMapping(value="messages/new")
    public String getNewMessage(Model model){
        model.addAttribute("newMessage", new Message());
        return "messages/new";
    }

    @PostMapping(value="messages/new")
    public String postNewMessage(@ModelAttribute("newMessage") Message newMessage, Model model, Authentication authentication){
        newMessage.setDate(new Date(System.currentTimeMillis()));
        messageService.create(newMessage.getText(), newMessage.getDate(), userDAO.getIdByUsername(authentication.getName()), userDAO.getIdByUsername(newMessage.getReceiver_username()));
        return messages(model, authentication);
    }

    @GetMapping(value="messages/chat/{id}")
    public String showChat(Model model, @PathVariable("id") int id, Authentication authentication){
        int userId = userDAO.getIdByUsername(authentication.getName());
        List<Message> messages = messageService.listAllMessagesWith(userId, id);
        for(Message message : messages){
            message.getFormattedText(userDAO);
        }
        model.addAttribute("messages", messages);
        model.addAttribute("receiver", id);
        model.addAttribute("newMessage", new Message());
        return "/messages/chat";
    }

    @PostMapping(value = "/messages/chat/{id}")
    public String postChat(@ModelAttribute("newMessage") Message newMessage, @PathVariable("id") int id, Model model, Authentication authentication){
        newMessage.setDate(new Date(System.currentTimeMillis()));
        messageService.create(newMessage.getText(), newMessage.getDate(), userDAO.getIdByUsername(authentication.getName()), id);
        return showChat(model, id, authentication);
    }
}
