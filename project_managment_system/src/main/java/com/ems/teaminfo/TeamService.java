package com.ems.teaminfo;

import com.ems.userinfo.User;

import java.util.List;

/**
 * Created by Martynas on 5/7/2017.
 */
public interface TeamService {
    List<User> getMembers(Integer team_id);
    Team getTeamByUser(Integer id);
}
