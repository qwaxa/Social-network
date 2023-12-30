package com.example.socialnetwork.service;

import com.example.socialnetwork.Enum.Role;
import com.example.socialnetwork.entity.Friend;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.security.UserDetailsimp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;



    public Optional<User> getUserById(Integer id){
        return userRepository.findUserById(id);
    }

    @Transactional
    public void save(User user, MultipartFile file) {
        try {
            user.setImage(file.getBytes());
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Some internal error occurred");
        }
    }
    public Optional<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsimp user = (UserDetailsimp) authentication.getPrincipal();
        return   userRepository.findByUsername(user.getUsername());
    }



}
