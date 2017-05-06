package com.ems.controllers;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectJDBC;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("currentProject", new Project());
        return "projects";
    }

    @RequestMapping(value="project-core", method = RequestMethod.GET)
        public String getProject(Model model){
        Project p = new Project("Pavadinimas", "Aprasymas", new Date(1,1,1));
        model.addAttribute("project", p);
        model.addAttribute("tasks", projectService.listAllTasks());
        model.addAttribute("subtasks", projectService.listAllSubtasks());
        return "project-core";
    }
}
