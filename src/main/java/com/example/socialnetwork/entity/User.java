package com.example.socialnetwork.entity;

import com.example.socialnetwork.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private  Integer id;
    @Column(name = "username")
    @Size(min = 2, max = 100)
    private  String username;
    @Column (name = "password")
    private String password;
    @Size(min = 2, max = 100)
    private  String firstname;
    @Size(min = 2, max = 100)
    private  String lastname;
    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @Column(name="image")
    private byte[] image;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    List<Post> posts;
    @Column (name = "bio")
    private String bio;
    @OneToMany(mappedBy = "user_comment",fetch = FetchType.EAGER)
    List<Comment> comments;
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    private List<Message> receivedMessages;

    public String generateBase64Image() {
        return Base64.encodeBase64String(this.image);
    }



}
