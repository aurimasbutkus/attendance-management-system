package com.ems.controllers;

/**
 * Created by Marius on 2017-04-07.
 */

import com.ems.userinfo.User;
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



    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/")
    public String login() {
        return "redirect:/login";
    }
    @GetMapping(value = "/login")
    public String getLogin(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }
    @PostMapping(value = "/login")
    public String postLogin(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        User loginUser = sql.getUser(userForm);
        loginValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            printErrors(bindingResult);
            return "login";
        }
        else if(loginUser.getPassword().equals(userForm.getPassword()))
            return "redirect:/index";
        else return "login";
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
            printErrors(bindingResult);
            return "register";
        }
        sql.create(userForm);
        model.addAttribute("username", userForm.getUsername());
        return "result";
    }
    private void printErrors(BindingResult bindingResult)
    {
        for (Object object : bindingResult.getAllErrors()) {
            if(object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                String message = fieldError.getField() + " " + messageSource.getMessage(fieldError, null);
                System.out.println(message);
            }
        }
    }
}
