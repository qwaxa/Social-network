package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_comment;
    @Column(columnDefinition = "text",nullable = false)
    private  String message;
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected  void  onCreate(){
        this.createdDate=LocalDateTime.now();
    }
    public String getFormattedCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdDate.format(formatter);
    }


}
