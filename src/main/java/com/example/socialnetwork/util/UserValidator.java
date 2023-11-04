package com.example.socialnetwork.util;

import com.example.socialnetwork.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {

    private final  UserDetailsService userDetailsService;
    @Autowired
    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
User user = (User) target;
try {
    userDetailsService.loadUserByUsername(user.getUsername());
}catch (UsernameNotFoundException ignored){
    return;//все ок пользователен не найден
}
errors.rejectValue("username","","Человек с таким логином уже существует");
    }

}
