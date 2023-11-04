package com.example.socialnetwork.service;

import com.example.socialnetwork.Enum.Role;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;
    public  void createUser(User user){


    }
}
