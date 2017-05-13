package com.ems.validator;

import com.ems.userinfo.Role;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Role role = (Role) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (role.getUsername().length() < 4 || role.getUsername().length() > 32) {
            errors.rejectValue("username", "userForm.username.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty");
        if (role.getRole().length() < 2 || role.getRole().length() > 32) {
            errors.rejectValue("role", "userForm.role.size");
        }
    }
}
