package com.example.socialnetwork.Controller;

import com.example.socialnetwork.entity.Friend;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.service.FriendService;
import com.example.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class FriendController {
    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @GetMapping("/friends")
    public String friends(Model model){
        model.addAttribute("friends",friendService.findFriendsByFirstUser_Id(userService.getCurrentUser().get().getId(),"ACCEPTED"));
        model.addAttribute("pendingFriends",friendService.findFriendsBySecondUser_Id(userService.getCurrentUser().get().getId(),"PENDING"));
        return "friends";
    }
    @PostMapping("/profile/addOrRemoveFriend")
    public String addOrRemoveFriend(@RequestParam("userId") Integer userId) {

        User currentUser = userService.getCurrentUser().get();
        // Получите пользователя, которого вы хотите добавить в друзья, по userId
        User friendToAdd = userService.getUserById(userId).get();
        // Проверьте, являются ли пользователи друзьями
        if (friendService.areFriends(currentUser, friendToAdd)) {

            friendService.removeFriend(currentUser, friendToAdd);
            friendService.removeFriend(friendToAdd,currentUser);
        } else {
            friendService.addFriend(currentUser, friendToAdd);
        }



        // Перенаправьте пользователя на страницу профиля
        return "redirect:/profile/" + userId;
    }
    @PostMapping("/acceptFriend")
    public String acceptFriend(@RequestParam("friendId") Integer id){
friendService.acceptFriend(id);
return "redirect:/friends";
    }
    @PostMapping("/rejectFriend")
    public String rejectFriend(@RequestParam("friendId") Integer id){
        friendService.rejectFriend(id);
        return "redirect:/friends";
    }
}
