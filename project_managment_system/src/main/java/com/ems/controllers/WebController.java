package com.ems.controllers;

/**
 * Created by Marius on 2017-04-07.
 */

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.User;
import com.ems.userinfo.UserJDBC;
import com.ems.userinfo.UserService;
import com.ems.validator.LoginValidator;
import com.ems.validator.ProjectValidator;
import com.ems.validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    private UserJDBC sql;
    @Autowired
    private RegisterValidator registerValidator;
    @Autowired
    private LoginValidator loginValidator;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectValidator projectValidator;

    @RequestMapping("/access-denied")
    public String access() {
        return "access-denied";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model, Authentication authentication){
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        model.addAttribute("projects", projectService.listAllUserProjects(user_id));
        model.addAttribute("userForm", userService.getUser(user_id));
        model.addAttribute("tasks", projectService.listAllTasks());
        model.addAttribute("currentUserName", authentication.getName());
        model.addAttribute("newProject", new Project());
        return "index";
    }
    @PostMapping(value="/index/new-project-submit")
    public String createNewProject(@ModelAttribute("newProject") Project newProject, BindingResult bindingResult,
                                   Model model, Authentication authentication){
        projectValidator.validate(newProject, bindingResult);
        if (bindingResult.hasErrors()) {
            return "index";
        }
        if(newProject.getDeadline() == null)
            projectService.create(newProject.getName(), newProject.getDescription(),
                    newProject.getStartDate());
        else
            projectService.create(newProject.getName(), newProject.getDescription(),
                    newProject.getStartDate(), newProject.getDeadline());
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        model.addAttribute("projects", projectService.listAllUserProjects(user_id));
        return "redirect:/index";
    }
    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping(value = "/login")
    public String getLogin(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }
    @PostMapping(value = "/login")
    public String postLogin(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        loginValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        else return "redirect:/index";
    }
    @GetMapping(value = "/register")
    public String getRegister(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }
    @PostMapping(value = "/register")
    public String postRegister(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        registerValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        sql.create(userForm);
        model.addAttribute("username", userForm.getUsername());
        return "result";
    }
}
