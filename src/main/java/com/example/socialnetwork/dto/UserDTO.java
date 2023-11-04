package com.example.socialnetwork.dto;

import com.example.socialnetwork.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
 private  String username;
 private  byte []  image;
 private List<Post> posts;

}
