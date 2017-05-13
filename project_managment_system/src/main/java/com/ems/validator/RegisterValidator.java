package com.ems.validator;

import com.ems.userinfo.User;
import com.ems.userinfo.UserJDBC;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegisterValidator implements Validator {
    @Autowired
    private UserService sql;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "userForm.username.size");
        }
        if (sql.getUser(user.getUsername()) != null) {
            errors.rejectValue("username", "userForm.username.duplicate");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty");
        if (user.getEmailAddress().length() < 6 || user.getEmailAddress().length() > 32) {
            errors.rejectValue("emailAddress", "userForm.emailAddress.size");
        }
        if (!user.getEmailAddress().contains("@")) {
            errors.rejectValue("emailAddress", "userForm.emailAddress.invalid");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "userForm.password.size");
        }
    }
}
