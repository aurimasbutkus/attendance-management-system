package com.ems.validator;

import com.ems.teaminfo.Team;
import com.ems.teaminfo.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Martynas on 5/17/2017.
 */
@Component
public class TeamValidator implements Validator {
    @Autowired
    private TeamService sql;

    @Override
    public boolean supports(Class<?> aClass) {
        return Team.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Team team = (Team) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (team.getName().length() < 4 || team.getName().length() > 20) {
            errors.rejectValue("name", "newTeam.name.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (team.getDescription().length() < 4 || team.getDescription().length() > 200) {
            errors.rejectValue("description", "newTeam.description.size");
        }
    }
}
