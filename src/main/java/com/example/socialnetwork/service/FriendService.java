package com.example.socialnetwork.service;


import com.example.socialnetwork.entity.Friend;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.FriendRepository;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    public void saveFriend(Friend friend){
        friendRepository.save(friend);
    }
    public Friend findFriendByUsers(User user1, User user2) {
        return friendRepository.findByFirstUserAndSecondUser(user1, user2)
                .orElseGet(() -> friendRepository.findByFirstUserAndSecondUser(user2, user1).orElse(null));
    }
    public void addFriend(User firstUser, User secondUser) {
        // Создаем запись о дружбе и сохраняем её в репозитории
        Friend friend = new Friend();
        friend.setFirstUser(firstUser);
        friend.setSecondUser(secondUser);
        friend.setStatus("PENDING");
        friendRepository.save(friend);
    }
    public void acceptFriend(Integer id) {
        friendRepository.deleteByFirstUserIdAndStatus(id,"PENDING");
        Friend friend1 = new Friend();
        friend1.setFirstUser(userRepository.findUserById(id).get());
        friend1.setSecondUser(userService.getCurrentUser().get());
        friend1.setStatus("ACCEPTED");
        friendRepository.save(friend1);
        friend1=new Friend();
        friend1.setFirstUser(userService.getCurrentUser().get());
        friend1.setSecondUser(userRepository.findUserById(id).get());
        friend1.setStatus("ACCEPTED");
        friendRepository.save(friend1);

    }
    public  void rejectFriend(Integer id){
        friendRepository.deleteByFirstUserIdAndStatus(id,"PENDING");
    }
    public void removeFriend(User firstUser, User secondUser) {
        // Находим запись о дружбе и удаляем её из репозитория
        Friend friendToRemove = friendRepository.findByFirstUserAndSecondUser(firstUser, secondUser)
                .orElseGet(() -> friendRepository.findByFirstUserAndSecondUser(secondUser, firstUser).orElse(null));

        if (friendToRemove != null) {
            friendRepository.delete(friendToRemove);
        }
    }
   public List<Friend> findFriendsByFirstUser_Id(Integer id,String status){
        return friendRepository.findFriendsByFirstUser_IdAndStatus(id,status);
    }
    public List<Friend> findFriendsBySecondUser_Id(Integer id,String status){
        return friendRepository.findFriendsBySecondUser_IdAndStatus(id,status);
    }
public  Friend findFriendByFirstUser_idAndSecondUser_Id_Status(Integer first_id,Integer second_id,String status){
        return  friendRepository.findFriendByFirstUser_IdAndSecondUser_IdAndStatus(first_id,second_id,status);
}
    public boolean areFriends(User user1, User user2) {
        // Проверяем, являются ли user1 и user2 друзьями
        Friend friend =findFriendByUsers(user1, user2);
        return friend != null;
    }
}
