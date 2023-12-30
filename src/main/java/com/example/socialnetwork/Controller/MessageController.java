package com.example.socialnetwork.Controller;

import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.service.FriendService;
import com.example.socialnetwork.service.MessageService;
import com.example.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    FriendService friendService;
    @Autowired
    UserService userService;
    @GetMapping("/messages/{id}")
    public  String getMessages(Model model, @PathVariable int id){
     //   model.addAttribute("messages",messageService.findMessagesBySender_IdOrRecipient_Id(userService.getCurrentUser().get().getId()));
        model.addAttribute("currentUser",userService.getCurrentUser());
        model.addAttribute("currentFriend",friendService.findFriendByFirstUser_idAndSecondUser_Id_Status(userService.getCurrentUser().get().getId(),id,"ACCEPTED"));
        model.addAttribute("friends",friendService.findFriendsByFirstUser_Id(userService.getCurrentUser().get().getId(),"ACCEPTED"));
        model.addAttribute("messages",messageService.getMessagesBetweenUsers(userService.getCurrentUser().get().getId(),id));
        return "messages";
    }
    @PostMapping("/sendMessage")
    public String sendMessage(
            @RequestParam("idRecipient") Integer idRecipient,
            @RequestParam("messageText") String messageText
    ) {
        // Получите текущего пользователя
        User currentUser = userService.getCurrentUser().get();

        // Получите получателя по id
        User recipient = userService.getUserById(idRecipient).get();

        // Отправьте сообщение
        messageService.sendMessage(currentUser, recipient, messageText);

        // Обновите модель, если это необходимо
        // ...

        // Верните страницу чата или другой результат
        return "redirect:/index";
    }
}
