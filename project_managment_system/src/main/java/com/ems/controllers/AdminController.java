package com.ems.controllers;

/**
 * Created by Marius on 2017-04-07.
 */

import com.ems.userinfo.User;
import com.ems.userinfo.UserDAO;
import com.ems.userinfo.UserJDBC;
import com.ems.validator.LoginValidator;
import com.ems.validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private UserDAO userService;

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String projects(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/admin";
    }    @RequestMapping(value="/admin/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/admin";
    }
    @GetMapping(value = "/admin/users/{id}")
    public String showEditForm(Model model, @PathVariable("id") int id )
    {
        model.addAttribute("userForm", userService.getUser(id));
        return "admin/edit";
    }
    @PostMapping(value = "/admin/users/{id}")
    public String confirmEditForm(@ModelAttribute("userForm") User userForm )
    {
        userService.updateEverything(userForm);
        return "redirect:/admin/users";
    }

}
