package com.example.socialnetwork.Controller;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.security.UserDetailsimp;
import com.example.socialnetwork.service.RegistationService;
import com.example.socialnetwork.service.UserService;
import com.example.socialnetwork.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private  final UserValidator userValidator;
    private  final RegistationService registationService;

    @Autowired
    public AuthController(UserValidator userValidator, RegistationService registationService, UserService userService) {
        this.userValidator = userValidator;
        this.registationService = registationService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user_reg", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String  performRegistration(@ModelAttribute @Valid User user,
                                       BindingResult bindingResult ){
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors())
            return "registration";
        registationService.register(user);
        return "redirect:/auth/login";
    }
    @GetMapping("/auth/login")
    public  String loginPage(){
        return  "login";
    }


    @GetMapping("/failure")
    public  String failure(){
        return  "failure";
    }
}
