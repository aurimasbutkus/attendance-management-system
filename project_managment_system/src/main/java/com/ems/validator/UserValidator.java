package com.ems.validator;

import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.User;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by MARTYNAS on 17/05/13.
 */

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService sql;

    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "userForm.username.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (user.getFirstName().length() < 2 || user.getUsername().length() > 32) {
            errors.rejectValue("firstName", "userForm.name.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        if (user.getLastName().length() < 2 || user.getUsername().length() > 32) {
            errors.rejectValue("lastName", "userForm.name.size");
        }
        if (sql.getUser(user.getUsername()) != null && sql.getUser(user.getId()) == null) {
            errors.rejectValue("username", "userForm.username.duplicate");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty");
        if (user.getEmailAddress().length() < 6 || user.getEmailAddress().length() > 32) {
            errors.rejectValue("emailAddress", "userForm.emailAddress.size");
        }
        if (!user.getEmailAddress().contains("@")) {
            errors.rejectValue("emailAddress", "userForm.emailAddress.invalid");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "NotEmpty");
    }
}
