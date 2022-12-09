package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostDto;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.post.Updater;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(Long userId, PostDto postDto){

        User user = userRepository.getReferenceById(userId);

        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();

        user.addPost(post);

        return postRepository.save(post).getId();
    }
    @Transactional
    public Long update(Long id, PostDto new_postDto){
        Post post = postRepository.getReferenceById(id);

        Updater.builder().post(post).build()
                ._title(new_postDto.getTitle())
                ._content(new_postDto.getContent());

        return post.getId();
    }
    public Long delete(Long id){

        Post post = postRepository.getReferenceById(id);
        post.getUser().deletePost(post);

        postRepository.deleteById(id);

        return id;
    }
}
