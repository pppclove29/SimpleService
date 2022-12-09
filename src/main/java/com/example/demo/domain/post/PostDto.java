package com.example.demo.domain.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDto {

    Long id;
    String title;
    String content;

    @Builder
    PostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }


}


