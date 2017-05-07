package com.ems.controllers;

import com.ems.messaging.MessageService;
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

    @RequestMapping(value="messages", method = RequestMethod.GET)
    public String messages(Model model, Authentication authentication){
        String name = authentication.getName();
        model.addAttribute("messages", messageService.listAllMessages(name));
        return "messages";
    }

    @RequestMapping(value="new-message", method = RequestMethod.GET)
    public String newMessage(Model model){

        return "new-message";
    }
}
