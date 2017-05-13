package com.ems.controllers;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Martynas on 5/5/2017.
 */
@Controller
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="projects", method = RequestMethod.GET)
    public String projects( Model model, Authentication authentication){
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        model.addAttribute("projects", projectService.listAllUserProjects(user_id));
        model.addAttribute("currentProject", new Project());
        return "projects";
    }

    @RequestMapping(value="project-core/{id}", method = RequestMethod.GET)
    public String getProject(@PathVariable("id") int id, Model model, Authentication authentication){
        //Project p = new Project("Pavadinimas", "Aprasymas", new Date(1,1,1));
        //model.addAttribute("project", p);
        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("tasks", projectService.listAllProjectTasks(id));
        model.addAttribute("subtasks", projectService.listAllSubtasks());
        return "project-core";
    }

    @GetMapping(value="projects/new")
    public String projectCreation( Model model){
        model.addAttribute("newProject", new Project());
        return "project-creation";
    }

    @PostMapping(value="projects/new-submit")
    public String createNewProject(@ModelAttribute("newProject") Project newProject, Model model, Authentication authentication){
        if(newProject.getDeadline() == null)
            projectService.create(newProject.getName(), newProject.getDescription(),
                                    newProject.getStartDate());
        else
            projectService.create(newProject.getName(), newProject.getDescription(),
                                    newProject.getStartDate(), newProject.getDeadline());

        return projects(model, authentication);
    }
}
