package com.vlad9pa.tasktest.validator;

import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsernameValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username","Duplicate");
        }
    }
}
