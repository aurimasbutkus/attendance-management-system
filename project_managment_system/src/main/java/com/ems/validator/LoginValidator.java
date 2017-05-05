package com.ems.validator;

import com.ems.userinfo.User;
import com.ems.userinfo.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {
    @Autowired
    private UserJDBC sql;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        System.out.print(sql.getUser(user.getUsername()).getPassword()+"   "+(user.getPassword()));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (sql.getUser(user.getUsername()) == null) {
            errors.rejectValue("username", "DoesNotExist.userForm.username");
        }
        else if (!sql.getUser(user.getUsername()).getPassword().equals(bCryptPasswordEncoder.encode(user.getPassword()))) {
            errors.rejectValue("password", "BadPassword.userForm.password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
    }
}
