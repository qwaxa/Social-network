package com.example.socialnetwork.Controller;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.security.UserDetailsimp;
import com.example.socialnetwork.service.FriendService;
import com.example.socialnetwork.service.PostService;
import com.example.socialnetwork.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    FriendService friendService;




    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable("id") int id){
        model.addAttribute("posts",postService.getUserPosts(id));
        model.addAttribute("currentUser",userService.getCurrentUser());
        model.addAttribute("userProfile",userService.getUserById(id));
        model.addAttribute("areFriends",friendService.areFriends(userService.getCurrentUser().get(),userService.getUserById(id).get()));
        model.addAttribute("friends",friendService.findFriendsByFirstUser_Id(id,"ACCEPTED"));
        return "profile";
    }
    @GetMapping("/index")
    public  String index(Model model){
        model.addAttribute("people",userRepository.findAll());
        model.addAttribute("currentUser",userService.getCurrentUser());
        model.addAttribute("posts",postService.getPosts());
        model.addAttribute("friends",friendService.findFriendsByFirstUser_Id(userService.getCurrentUser().get().getId(),"ACCEPTED"));
        return "index";
    }
    @PostMapping (value = "profile/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestPart("file") MultipartFile file){
        userService.save(userService.getCurrentUser().get(), file);
return "redirect:/profile/"+userService.getCurrentUser().get().getId();
    }

    @PostMapping("/updateIntro")
    public String updateIntro(@RequestParam String introText) {
        User currentUser = userService.getCurrentUser().get();
        currentUser.setBio(introText);
        userRepository.save(currentUser);
        return "redirect:/profile/"+currentUser.getId();
    }


}

