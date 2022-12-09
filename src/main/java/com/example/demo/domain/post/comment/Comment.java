package com.example.demo.domain.post.comment;

import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "COMMENTS")
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    Post post;

    String content;

    @Builder
    Comment(String content) {
        this.content = content;
    }

    public void setUser(User user){
        this.user = user;
    }
    public void setPost(Post post){
        this.post = post;
    }
    public void changeComment(String content){
        this.content = content;
    }
}
