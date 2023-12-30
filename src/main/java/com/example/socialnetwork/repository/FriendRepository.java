package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Friend;
import com.example.socialnetwork.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {
  List<Friend> findFriendsByFirstUser_IdAndStatus(Integer firstUserId,String Status);
  List<Friend> findFriendsBySecondUser_IdAndStatus(Integer firstUserId,String Status);
 Friend findFriendByFirstUser_Id(Integer id);

    // Найти запись о дружбе по двум пользователям
    Optional<Friend> findByFirstUserAndSecondUser(User firstUser, User secondUser);
    Friend findFriendByFirstUser_IdAndSecondUser_IdAndStatus(Integer first_id,Integer second_id,String status);
    @Transactional
    void deleteByFirstUserIdAndStatus(Integer userId,String status);

}
