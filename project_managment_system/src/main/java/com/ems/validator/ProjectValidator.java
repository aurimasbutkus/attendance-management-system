package com.ems.validator;

import com.ems.projectsinfo.Project;
import com.ems.userinfo.Role;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProjectValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Project project = (Project) o;

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
