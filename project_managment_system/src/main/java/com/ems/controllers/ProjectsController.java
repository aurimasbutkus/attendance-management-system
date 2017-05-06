package com.ems.controllers;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectJDBC;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;


/**
 * Created by Martynas on 5/5/2017.
 */
@Controller
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value="projects", method = RequestMethod.GET)
    public String projects(Model model){
        model.addAttribute("projects", projectService.listAllProjects());
        return "projects";
    }
}
