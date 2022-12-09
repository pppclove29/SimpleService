package com.example.demo.domain.post.comment;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentDto {

    Long id;
    String content;


    @Builder
    CommentDto(String content){
        this.content=content;
    }
}
