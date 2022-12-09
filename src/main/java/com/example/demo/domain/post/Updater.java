package com.example.demo.domain.post;

import lombok.Builder;
import org.springframework.transaction.annotation.Transactional;

// 게시글 업데이트를 위해 고안
@Transactional
public class Updater{

    Post post;

    @Builder
    Updater(Post post){
        this.post= post;
    }

    public Updater _title(String title){
        post.title = title;
        return this;
    }
    public Updater _content(String content){
        post.content = content;
        return this;
    }
}