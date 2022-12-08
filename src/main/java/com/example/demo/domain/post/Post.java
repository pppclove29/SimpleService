package com.example.demo.domain.post;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

}
