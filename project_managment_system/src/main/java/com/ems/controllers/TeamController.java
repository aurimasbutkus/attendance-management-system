package com.ems.controllers;

import com.ems.teaminfo.Team;
import com.ems.teaminfo.TeamService;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("team_info", team);
        return "team";
    }
}
