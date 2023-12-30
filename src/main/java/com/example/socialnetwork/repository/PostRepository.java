package com.example.socialnetwork.repository;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository <Post, Integer> {
    List<Post> findPostsByUser(User user);
    Post findPostById(Integer id);
    List<Post> findPostsByUser_IdOrderByCreatedDateDesc(Integer userId);
    List<Post> findAllByOrderByCreatedDateDesc();
}
