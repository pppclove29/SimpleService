package com.example.demo.domain;

import com.example.demo.domain.post.Post;
import com.example.demo.api.dto.PostDto;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.user.User;
import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @BeforeEach
    void clearDB() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 게시글등록() {
        //given
        UserDto userDto = createUser();

        Long userId = userService.register(userDto);

        String title = "테스트 제목";
        String content = "테스트 내용";

        PostDto postDto = createPost(title, content);

        //when
        Long postId = postService.save(userId, postDto);

        //then
        Post post = postRepository.getReferenceById(postId);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);

        assertThat(post.getCreatedTime()).isBefore(LocalDateTime.now());

        assertThat(post.getUser().getId()).isEqualTo(userId);
    }

    @Test
    public void 게시글수정() {
        //given
        UserDto userDto = createUser();

        Long userId = userService.register(userDto);

        String title = "테스트 제목";
        String content = "테스트 내용";

        PostDto postDto = createPost(title, content);

        Long postId = postService.save(userId, postDto);

        String newTitle = "새로운 테스트 제목";
        String newContent = "새로운 테스트 내용";

        PostDto newPostDto = createPost(newTitle, newContent);

        //when
        postService.update(postId, newPostDto);

        //then
        Post post = postRepository.getReferenceById(postId);

        assertThat(post.getTitle()).isEqualTo(newTitle);
        assertThat(post.getContent()).isEqualTo(newContent);

        assertThat(post.getModifiedTime()).isBefore(LocalDateTime.now());

        assertThat(post.getUser().getId()).isEqualTo(userId);
    }

    @Test
    public void 게시글삭제() {
        //given
        UserDto userDto = createUser();

        Long userId = userService.register(userDto);

        String title = "테스트 제목";
        String content = "테스트 내용";

        PostDto postDto = createPost(title, content);

        Long postId = postService.save(userId, postDto);

        //when
        postService.delete(postId);

        //then
        assertThat(postRepository.findAll().size()).isEqualTo(0);

        User user = userRepository.getReferenceById(userId);

        assertThat(user.getPosts().size()).isEqualTo(0);
    }

    UserDto createUser() {
        UserDto dto = new UserDto();
        dto.setName("임시");
        dto.setPassword("임시");

        return dto;
    }
    PostDto createPost(String title, String content) {
        PostDto dto = new PostDto();
        dto.setTitle(title);
        dto.setContent(content);

        return dto;
    }
}


