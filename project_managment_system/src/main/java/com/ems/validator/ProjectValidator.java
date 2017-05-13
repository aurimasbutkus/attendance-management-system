package com.ems.validator;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by MARTYNAS on 17/05/13.
 */

@Component
public class ProjectValidator implements Validator {
    @Autowired
    private ProjectService sql;

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (project.getName().length() < 4 || project.getName().length() > 20) {
            errors.rejectValue("name", "newProject.name.size");
        }
        if (sql.getProject(project.getName()) != null) {
            errors.rejectValue("name", "newProject.name.duplicate");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (project.getDescription().length() < 4 || project.getDescription().length() > 200) {
            errors.rejectValue("name", "newProject.description.size");
        }
    }
}
