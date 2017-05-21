package com.ems.controllers;

import com.ems.projectsinfo.ProjectService;
import com.ems.teaminfo.Team;
import com.ems.teaminfo.TeamService;
import com.ems.userinfo.User;
import com.ems.userinfo.UserService;
import com.ems.validator.TeamValidator;
import org.codehaus.groovy.tools.shell.util.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Martynas on 5/7/2017.
 */
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TeamValidator teamValidator;

    @Autowired
    private org.springframework.context.MessageSource messageSource;

    @RequestMapping(value="team")
    public String team(Model model, Authentication authentication){
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        Team team = teamService.getTeamByUser(user_id);
        model.addAttribute("currentUserName", username);
        model.addAttribute("projects", projectService.listAllUserProjects(user_id));
        model.addAttribute("members", teamService.getMembers(team.getId()));
        model.addAttribute("newMember", new User());
        model.addAttribute("team_info", team);
        model.addAttribute("team_projects", teamService.getProjects(team.getId()));
        model.addAttribute("tasks", projectService.listAllTasks());
        return "team";
    }

    @PostMapping(value="team/addMember/{id}")
    public String addMemberToTeam(@ModelAttribute("newMember") User newMember, @PathVariable("id") Integer team_id, BindingResult bindingResult,
                                  Model model, Authentication authentication)
    {
        Team team = teamService.getTeamById(team_id);
        model.addAttribute("members", teamService.getMembers(team.getId()));
        model.addAttribute("newMember", new User());
        model.addAttribute("team_info", team);
        model.addAttribute("team_projects", teamService.getProjects(team.getId()));
        User user = userService.getUser(newMember.getUsername());
        if(user == null) return team(model, authentication);
        teamService.addMemberToTeam(team_id, user.getId());
        model.addAttribute("members", teamService.getMembers(team.getId()));
        return "team";
    }

    @RequestMapping(value="team/removeMember/{id}", method = RequestMethod.POST)
    public String removeMemberFromTeam(@PathVariable("id") Integer user_id)
    {
        teamService.removeMemberFromTeam(user_id);
        return "redirect:/team";
    }

    @GetMapping(value="team/new")
    public String teamCreation( Model model, Authentication authentication){
        model.addAttribute("newTeam", new Team());
        String username = authentication.getName();
        Integer userId = userService.getUser(username).getId();
        model.addAttribute("projects", projectService.listAllUserProjects(userId));
        return "team-creation";
    }

}
