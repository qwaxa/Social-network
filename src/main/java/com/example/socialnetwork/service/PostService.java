package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.Comment;
import com.example.socialnetwork.entity.Post;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.CommentRepository;
import com.example.socialnetwork.repository.PostRepository;
import com.example.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
 @Autowired
    CommentRepository commentRepository;
 @Autowired
 UserService userService;
    // Метод для создания поста
    @Transactional
    public void createPost( String content, MultipartFile file, User user) {
        Post post = new Post();
        post.setContent(content);
        try {
            post.setPostImage(file.getBytes());
        } catch (IOException e) {
            System.out.println("Some internal error occurred");
        }
        post.setUser(user);
        post.setLike(0);

        postRepository.save(post);
    }

    public List<Post> postList(){
        return postRepository.findAll();
    }


    public void savePost(Post post) {
        postRepository.save(post);
    }


    public List<Post> getUserPosts(Integer user_id){
        return postRepository.findPostsByUser_IdOrderByCreatedDateDesc(user_id);
    }

    public Post getPostById(int postId) {
    return     postRepository.findPostById(postId);
    }
    public List<Comment> getCommentsForPost(Post post) {
        return commentRepository.findByPostOrderByCreatedDateDesc(post);
    }
@Transactional
    public void addCommentToPost(Post post, String content) {
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser_comment(userService.getCurrentUser().get());
        comment.setMessage(content);
        commentRepository.save(comment);
    }
    public List<Post> getPosts(){
        return  postRepository.findAllByOrderByCreatedDateDesc();
    }
    public  void deletePost(Integer postId){
        postRepository.deleteById(postId);
    }
}
