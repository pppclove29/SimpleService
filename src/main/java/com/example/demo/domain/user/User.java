package com.example.demo.domain.user;

import com.example.demo.domain.post.Post;
import com.example.demo.except.WrongPasswordException;
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
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Post> posts = new ArrayList<>();

    String name;
    String password;

    @Builder
    User(String name, String password){
        this.name=name;
        this.password=password;
    }

    public Long getId(){return id;}
    public void addPost(Post post){
        posts.add(post);
        // 새로 추가된 포스터에 해당 유저를 참조시킬것
    }
    public void deletePost(Post post){
        posts.remove(post);
        // 삭제된 포스트 자체를 db에서 삭제
    }

    public void checkPassword(String password)  {
        if(!password.equals(this.password))
            throw new WrongPasswordException("비밀번호가 일치하지 않습니다.");
    }

    public void changeConfig(String new_name, String new_password){
        this.name = new_name;
        this.password = new_password;
    }
}
