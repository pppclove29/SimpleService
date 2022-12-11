package com.example.demo.domain.post;

import com.example.demo.api.dto.PostDto;
import com.example.demo.domain.BaseEntity;
import com.example.demo.domain.post.comment.Comment;
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    String title;
    String content;
    String author;

    @Builder
    Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public void setUser(User user){
        this.user = user;
        author = user.getName();
    }
    public void addComment(Comment comment){
        comment.setPost(this);

        comments.add(comment);
    }
    public void deleteComment(Comment comment){
        comments.remove(comment);
    }

    public PostDto toDto(){
        PostDto dto = new PostDto();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setAuthor(author);

        return dto;
    }
}


