package com.example.demo.api.dto;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    String content;
    String author;
}
