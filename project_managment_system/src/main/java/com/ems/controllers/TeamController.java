package com.ems.controllers;

import com.ems.teaminfo.Team;
import com.ems.teaminfo.TeamService;
import com.ems.userinfo.User;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Martynas on 5/7/2017.
 */
@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="team")
    public String team(Model model, Authentication authentication){
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        Team team = teamService.getTeamByUser(user_id);
        model.addAttribute("members", teamService.getMembers(team.getId()));
        model.addAttribute("newMember", new User());
        model.addAttribute("team_info", team);
        model.addAttribute("team_projects", teamService.getProjects(team.getId()));
        return "team";
    }

    @PostMapping(value="team/addMember/{id}")
    public String addMemberToTeam(@ModelAttribute("newMember") User newMember, @PathVariable("id") Integer team_id, BindingResult bindingResult,
                                  Model model, Authentication authentication)
    {
        Team team = teamService.getTeamById(team_id);
        User user = userService.getUser(newMember.getUsername());
        teamService.addMemberToTeam(team_id, user.getId());
        String username = authentication.getName();
        Integer user_id = userService.getUser(username).getId();
        model.addAttribute("members", teamService.getMembers(team.getId()));
        model.addAttribute("newMember", new User());
        model.addAttribute("team_info", team);
        model.addAttribute("team_projects", teamService.getProjects(team.getId()));
        return "team";
    }
}
