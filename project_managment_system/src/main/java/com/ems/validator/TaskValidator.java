package com.ems.validator;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.projectsinfo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by MARTYNAS on 17/05/13.
 */

@Component
public class TaskValidator implements Validator {
    @Autowired
    private ProjectService sql;

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (task.getDescription().length() < 4 || task.getDescription().length() > 255) {
            errors.rejectValue("description", "newProject.description.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creationDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fkProject", "NotEmpty");
    }
}
