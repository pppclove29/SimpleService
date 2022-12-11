package com.example.demo.domain.user;

import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.comment.Comment;
import com.example.demo.except.WrongPasswordException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    String name;
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Builder
    User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public void setRole(Role role){
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void addPost(Post post) {
        post.setUser(this);

        posts.add(post);
    }
    public void addComment(Comment comment){
        comment.setUser(this);

        comments.add(comment);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }
    public void deleteComment(Comment comment){
        comments.remove(comment);
    }

    public void checkPassword(String password) {
        if (!password.equals(this.password))
            throw new WrongPasswordException("비밀번호가 일치하지 않습니다.");
    }

    public void changeConfig(String new_name, String new_password) {
        this.name = new_name;
        this.password = new_password;
    }
    public UserDto toDto(){
        UserDto userdto = new UserDto();
        userdto.setName(name);
        userdto.setPassword(password);

        return userdto;
    }
}
