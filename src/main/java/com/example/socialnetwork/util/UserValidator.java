package com.example.socialnetwork.util;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;
    @Autowired
    public UserValidator( UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
User user = (User) target;
if(userRepository.findByUsername(user.getUsername()).isPresent())
    errors.rejectValue("username","","Человек с таким логином уже существует");

    }

}
