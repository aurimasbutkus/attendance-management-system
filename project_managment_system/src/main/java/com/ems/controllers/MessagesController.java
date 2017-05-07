package com.ems.controllers;

import com.ems.messaging.MessageService;
import com.ems.userinfo.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("messages", messageService.listAllMessages(userId));
        return "messages";
    }

    @RequestMapping(value="new-message", method = RequestMethod.GET)
    public String newMessage(Model model){

        return "new-message";
    }
}
