package com.ems.controllers;

import com.ems.messaging.Message;
import com.ems.messaging.MessageService;
import com.ems.messaging.TeamMessage;
import com.ems.projectsinfo.ProjectService;
import com.ems.teaminfo.TeamService;
import com.ems.userinfo.User;
import com.ems.userinfo.UserService;
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
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value="/messages", method = RequestMethod.GET)
    public String messages(Model model, Authentication authentication){
        int userId = userService.getIdByUsername(authentication.getName());
        int teamId = userService.getUser(userId).getFkTeam();
        List<User> users = messageService.listAllInteractedUsers(userId);
        model.addAttribute("users", users);
        model.addAttribute("userTeamId", teamId);
        model.addAttribute("newUserMessage", new Message());
        model.addAttribute("currentUserName", authentication.getName());
        model.addAttribute("tasks", projectService.listAllTasks());
        model.addAttribute("projects", projectService.listAllUserProjects(userId));
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
        if(userService.userExists(newMessage.getReceiverUsername())){
            int id = userService.getIdByUsername(newMessage.getReceiverUsername());
            messageService.create(newMessage.getText(), newMessage.getDate(), userService.getIdByUsername(authentication.getName()), userService.getIdByUsername(newMessage.getReceiverUsername()));
            return showChat(model, id, authentication);
        }
        else return messages(model, authentication);
    }

    @GetMapping(value="messages/chat/{id}")
    public String showChat(Model model, @PathVariable("id") int id, Authentication authentication){
        int userId = userService.getIdByUsername(authentication.getName());
        List<Message> messages = messageService.listAllMessagesWith(userId, id);
        for(Message message : messages){
            message.setReceiverUsername(userService.getUser(message.getFkAccountReceiver()).getUsername());
        }
        int teamId = userService.getUser(userId).getFkTeam();
        List<User> users = messageService.listAllInteractedUsers(userId);
        model.addAttribute("users", users);
        model.addAttribute("userTeamId", teamId);
        model.addAttribute("messages", messages);
        model.addAttribute("receiver", id);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("newUserMessage", new Message());
        model.addAttribute("currentUserName", authentication.getName());
        model.addAttribute("receiverUserName",  userService.getUser(id).getUsername());
        model.addAttribute("tasks", projectService.listAllTasks());
        model.addAttribute("projects", projectService.listAllUserProjects(userId));
        return "/messages/chat";
    }

    @PostMapping(value = "/messages/chat/{id}")
    public String postChat(@ModelAttribute("newMessage") Message newMessage, @PathVariable("id") int id, Model model, Authentication authentication){
        newMessage.setDate(new Date(System.currentTimeMillis()));
        messageService.create(newMessage.getText(), newMessage.getDate(), userService.getIdByUsername(authentication.getName()), id);
        return showChat(model, id, authentication);
    }

    @GetMapping(value="messages/team/{id}")
    public String showTeamChat(Model model, @PathVariable("id") int id, Authentication authentication){

        int userId = userService.getIdByUsername(authentication.getName());
        List<TeamMessage> messages = messageService.listAllTeamMessages(userService.getUser(userId).getFkTeam());
        for(TeamMessage message : messages){
            message.setSenderUsername(userService.getUser(message.getFkAccountSender()).getUsername());
        }
        int teamId = userService.getUser(userId).getFkTeam();
        List<User> users = messageService.listAllInteractedUsers(userId);
        model.addAttribute("users", users);
        model.addAttribute("userTeamId", teamId);
        model.addAttribute("messages", messages);
        model.addAttribute("receiver", id);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("newUserMessage", new Message());
        model.addAttribute("currentUserName", authentication.getName());
        model.addAttribute("teamName",  teamService.getTeamById(id).getName());
        model.addAttribute("tasks", projectService.listAllTasks());
        model.addAttribute("projects", projectService.listAllUserProjects(userId));
        return "/messages/team";
    }

    @PostMapping(value = "/messages/team/{id}")
    public String postTeamChat(@ModelAttribute("newMessage") TeamMessage newMessage, @PathVariable("id") int id, Model model, Authentication authentication){
        newMessage.setDate(new Date(System.currentTimeMillis()));
        messageService.createTeamMessage(newMessage.getText(), newMessage.getDate(), userService.getIdByUsername(authentication.getName()), id);
        return showTeamChat(model, id, authentication);
    }
}
