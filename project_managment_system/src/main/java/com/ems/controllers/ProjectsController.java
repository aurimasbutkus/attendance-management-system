package com.ems.controllers;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.projectsinfo.Subtask;
import com.ems.projectsinfo.Task;
import com.ems.userinfo.UserService;
import com.ems.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * Created by Martynas on 5/5/2017.
 */
@Controller
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value="project/{id}", method = RequestMethod.GET)
    public String getProject(@PathVariable("id") int id, Model model, Authentication authentication){
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        model.addAttribute("projects", projectService.listAllUserProjects(user_id));
        model.addAttribute("currentUserName", authentication.getName());
        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("tasks", projectService.listAllProjectTasks(id));
        model.addAttribute("subtasks", projectService.listAllSubtasks());
        model.addAttribute("newTask",new Task());
        model.addAttribute("newSubtask",new Subtask());
        //model.addAttribute("tasks", projectService.listAllTasks());
        return "project";
    }

    @PostMapping(value="project/{id}/add-task")
    public String addTask(@ModelAttribute("newTask") Task newTask, BindingResult bindingResult,
                          @PathVariable("id") Integer id, Model model, Authentication authentication){
        newTask.setFkProject(id);
        newTask.setCreationDate(new Date(System.currentTimeMillis()));
        projectService.create(newTask);
        return "redirect:/project/{id}";
    }

    @RequestMapping(value="project/edit-subtask", method=RequestMethod.POST)
    public @ResponseBody boolean editSubtask(@RequestBody Subtask subtask, HttpServletRequest request, HttpServletResponse response)
    {
        projectService.updateSubtask(subtask);
        return true;
    }

    @RequestMapping(value="/project/{project_id}/remove-task/{task_id}", method = RequestMethod.POST)
    public String removeTask(@PathVariable("project_id") Integer project_id, @PathVariable("task_id") Integer task_id) {
        projectService.deleteTask(task_id);
        return "redirect:/project/{project_id}";
    }
    @PostMapping(value="project/{project_id}/add-subtask/{task_id}")
    public String addTask(@ModelAttribute("newSubtask") Subtask newSubtask, BindingResult bindingResult,
                          @PathVariable("project_id") Integer project_id,
                          @PathVariable("task_id") Integer task_id, Model model,
                          Authentication authentication){
        newSubtask.setFkTask(task_id);
        newSubtask.setStatus(2);
        projectService.create(newSubtask);
        return "redirect:/project/{project_id}";
    }

    @RequestMapping(value="project/{project_id}/remove-subtask/{subtask_id}", method = RequestMethod.GET)
    public String removeSubtask(@PathVariable("project_id") Integer project_id,
                                @PathVariable("subtask_id") Integer subtask_id,Model model,
                                Authentication authentication){
        projectService.deleteSubtask(subtask_id);
        return "redirect:/project/{project_id}";
    }

    @GetMapping(value="projects/new")
    public String projectCreation( Model model, Authentication authentication){
        model.addAttribute("newProject", new Project());
        String username = authentication.getName();
        Integer userId = userService.getUser(username).getId();
        model.addAttribute("projects", projectService.listAllUserProjects(userId));
        return "project-creation";
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
