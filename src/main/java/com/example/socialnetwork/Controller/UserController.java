package com.example.socialnetwork.Controller;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.security.UserDetailsimp;
import com.example.socialnetwork.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/ShowUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsimp user = (UserDetailsimp) authentication.getPrincipal();
        System.out.println( user);
        return "hello";
    }
}
