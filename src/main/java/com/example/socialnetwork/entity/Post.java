package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name="posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Integer post_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_user_id",referencedColumnName = "user_id")
    private  User user;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "content")
    private String content;
    @Column(name="like")
    private Integer like;
}
