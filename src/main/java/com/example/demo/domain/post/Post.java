package com.example.demo.domain.post;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.post.comment.Comment;
import com.example.demo.domain.post.comment.CommentDto;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "POSTS")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;

    @OneToMany(mappedBy = "post")
    List<Comment> comments = new ArrayList<>();

    String title;
    String content;

    @Builder
    Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void addComment(Comment comment){
        comment.setPost(this);

        comments.add(comment);
    }
    public void deleteComment(Comment comment){
        comments.remove(comment);
    }
}


