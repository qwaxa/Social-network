package com.example.socialnetwork.service;

import com.example.socialnetwork.Enum.Role;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistationService {
    private  final PasswordEncoder passwordEncoder;
private  final UserRepository userRepository;
@Autowired
    public RegistationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Transactional
    public void  register(User user){
        user.getRoles().add(Role.ROLE_USER);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }
}
