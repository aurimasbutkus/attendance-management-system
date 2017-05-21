package com.ems.validator;

import com.ems.messaging.Message;
import com.ems.messaging.MessageService;
import com.ems.projectsinfo.Project;
import com.ems.projectsinfo.ProjectService;
import com.ems.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by Marius on 17/05/13.
 */

@Component
public class MessageValidator implements Validator {
    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Project.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Message message = (Message) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "NotEmpty");
        if (message.getText().length() < 0 || message.getText().length() > 255) {
            errors.rejectValue("text", "message.text.size");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fkAccountSender", "NotEmpty");
        if (userService.getUser(message.getFkAccountSender()) == null) {
            errors.rejectValue("fkAccountSender", "message.receiver.notExist");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverUsername", "NotEmpty");
        if (userService.getUser(message.getFkAccountReceiver()) == null) {
            errors.rejectValue("receiverUsername", "message.sender.notExist");
        }
    }
}
