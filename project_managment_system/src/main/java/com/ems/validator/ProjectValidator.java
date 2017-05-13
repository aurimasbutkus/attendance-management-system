package com.ems.validator;

import com.ems.projectsinfo.Project;
<<<<<<< HEAD
import com.ems.userinfo.Role;
import com.ems.userinfo.UserService;
=======
import com.ems.projectsinfo.ProjectService;
>>>>>>> e7dcf9bf6b8478b80b8d66f7dfb48c6649aa6d59
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

<<<<<<< HEAD
@Component
public class ProjectValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
=======
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
>>>>>>> e7dcf9bf6b8478b80b8d66f7dfb48c6649aa6d59
    }

    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;

<<<<<<< HEAD
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (project.getName().length() < 4 || project.getName().length() > 32) {
            errors.rejectValue("username", "userForm.username.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (project.getDescription().length() < 2 || project.getDescription().length() > 32) {
            errors.rejectValue("description", "userForm.role.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "NotEmpty");
//        if (project.getStartDate().length() < 2 || project.getRole().length() > 32) {
//            errors.rejectValue("startDate", "userForm.role.size");
//        }
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty");
//        if (project.getDescription().length() < 2 || project.getRole().length() > 32) {
//            errors.rejectValue("role", "userForm.role.size");
//        }
    }
}
=======
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
>>>>>>> e7dcf9bf6b8478b80b8d66f7dfb48c6649aa6d59
