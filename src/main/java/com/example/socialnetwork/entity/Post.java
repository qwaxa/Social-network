package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
@Data
@Table(name="posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_user_id",referencedColumnName = "user_id")
    private  User user;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @Column(name = "content")
    private String content;
    @Column(name="post_like")
    private Integer like;
    @ElementCollection
    @CollectionTable(name = "post_likes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "liked_user_ids")
    private List<Integer> likedUserIds = new ArrayList<>();
    @Column(name="image")
    private byte[] postImage;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();



    public String generateBase64Image() {
        return Base64.encodeBase64String(this.postImage);
    }

    @PrePersist
    protected  void  onCreate(){
        this.createdDate= LocalDateTime.now();
    }
    public String getFormattedCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdDate.format(formatter);
    }

}
