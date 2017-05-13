package com.ems.controllers;

/**
 * Created by Marius on 2017-04-07.
 */

import com.ems.messaging.Message;
import com.ems.messaging.MessageService;
import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.Role;
import com.ems.userinfo.RoleService;
import com.ems.userinfo.User;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MessageService messageService;   
    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String projects(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "admin/admin";
    }    
    @RequestMapping(value="/admin/user", method = RequestMethod.GET)
    public String user(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "admin/user";
    }    
    @GetMapping(value = "/admin/user/{id}")
    public String userEditGet(Model model, @PathVariable("id") int id )
    {
        model.addAttribute("userForm", userService.getUser(id));
        return "admin/user-edit";
    }
    @PostMapping(value = "/admin/user/{id}")
    public String userEditPost(@ModelAttribute("userForm") User userForm )
    {
        userService.updateEverything(userForm);
        return "redirect:/admin/user";
    }   
    @RequestMapping(value="/admin/project", method = RequestMethod.GET)
    public String project(Model model) {
        model.addAttribute("projects", projectService.listAllProjects());
        return "admin/project";
    }
    @GetMapping(value = "/admin/project/{id}")
    public String projectEditGet(Model model, @PathVariable("id") int id )
    {
        model.addAttribute("projectForm", projectService.getProject(id));
        return "admin/project-edit";
    }
    @PostMapping(value = "/admin/message/{id}")
    public String messageEditPost(@ModelAttribute("messageForm") Message messageForm )
    {
        messageService.updateEverything(messageForm);
        return "redirect:/admin/message";
    } 
    @RequestMapping(value="/admin/message", method = RequestMethod.GET)
    public String message(Model model) {
        model.addAttribute("messages", messageService.listAllMessages());
        return "admin/message";
    }
    @GetMapping(value = "/admin/message/{id}")
    public String messageEditGet(Model model, @PathVariable("id") int id )
    {
        model.addAttribute("messageForm", messageService.getMessage(id));
        return "admin/message-edit";
    }
    @PostMapping(value = "/admin/project/{id}")
    public String projectEditPost(@ModelAttribute("projectForm") Project projectForm )
    {
        projectService.updateEverything(projectForm);
        return "redirect:/admin/project";
    }   
    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    public String role(Model model) {
        model.addAttribute("roles", roleService.listAllRoles());
        return "admin/role";
    }
    @GetMapping(value = "/admin/role/create")
    public String roleCreateGet(Model model) {
        model.addAttribute("roleForm", new Role());
        return "admin/role-create";
    }
    @PostMapping(value = "/admin/role/create")
    public String roleCreatePost(@ModelAttribute("roleForm") Role roleForm )
    {
        roleService.createRole(roleForm);
        return "redirect:/admin/role";
    }
    @GetMapping(value = "/admin/role/{id}")
    public String roleEditGet(Model model, @PathVariable("id") int id )
    {
        model.addAttribute("roleForm", roleService.getRole(id));
        return "admin/role-edit";
    }
    @PostMapping(value = "/admin/role/{id}")
    public String roleEditPost(@ModelAttribute("roleForm") Role roleForm )
    {
        roleService.updateEverything(roleForm);
        return "redirect:/admin/role";
    }
}
