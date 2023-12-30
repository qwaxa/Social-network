package com.example.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="friends")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @OneToOne()
    @JoinColumn(name = "first_user_id",referencedColumnName = "user_id")
    User firstUser;
    @OneToOne()
    @JoinColumn(name = "second_user_id",referencedColumnName = "user_id")
    User secondUser;

    @Column
    private String status;

}
