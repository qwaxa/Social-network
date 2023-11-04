package com.example.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="friends")
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_user_id",referencedColumnName = "user_id")
    User firstUser;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_user_id",referencedColumnName = "user_id")
    User secondUser;

}
