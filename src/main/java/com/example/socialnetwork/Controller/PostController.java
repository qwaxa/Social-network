package com.example.socialnetwork.Controller;

import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.service.PostService;
import com.example.socialnetwork.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller

@RequiredArgsConstructor
public class PostController {
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @PostMapping(value = "/createPost",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createPost(String content, @RequestPart("postImage") MultipartFile file){
            postService.createPost(content,file,userService.getCurrentUser().get());
        return "redirect:/profile/" + userService.getCurrentUser().get().getId();
    }
    @PostMapping("/likePost")
    public String likePost(@RequestParam int postId) {
     User currentUser=   userService.getCurrentUser().get();
        Post post = postService.getPostById(postId);

        if (post != null) {
            List<Integer> likedUserIds = post.getLikedUserIds();

            // Проверка, лайкал ли пользователь уже этот пост
            if (!likedUserIds.contains(currentUser.getId())) {
                post.setLike(post.getLike() + 1);
                likedUserIds.add(currentUser.getId());
                postService.savePost(post);
            }
            else{
                post.setLike(post.getLike()-1);
                likedUserIds.remove(currentUser.getId());
                postService.savePost(post);
            }
        }

        return "redirect:/index";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam("postId") int postId,
                             @RequestParam("content") String content) {
        Post post = postService.getPostById(postId);
        if (post != null) {
            postService.addCommentToPost(post, content);
        }
        return "redirect:/index";
    }
    @PostMapping("/deletePost")
    public String deletePost(@RequestParam int postId) {
        Post post = postService.getPostById(postId);

        if (post != null && post.getUser().getId().equals(userService.getCurrentUser().get().getId())) {
            // Удалить пост только, если текущий пользователь является создателем поста
            postService.deletePost(postId);
        }

        return "redirect:/index";
    }
}
