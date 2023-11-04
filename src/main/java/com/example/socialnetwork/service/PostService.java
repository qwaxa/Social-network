package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    // Метод для создания поста
    public Post createPost(User user, String content, LocalDateTime createdDate, Integer like) {
        Post post = new Post();
        post.setUser(user);
        post.setContent(content);
        post.setCreatedDate(new Date());
        post.setLike(0);
        return postRepository.save(post);
    }


}
