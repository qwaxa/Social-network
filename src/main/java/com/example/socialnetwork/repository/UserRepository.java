package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository   extends JpaRepository<User,Integer> {

   Optional<User> findUserById(Integer id);
   Optional<User>  findByUsername(String username);


}
