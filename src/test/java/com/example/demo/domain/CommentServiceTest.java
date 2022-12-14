package com.example.demo.domain;

import com.example.demo.domain.post.Post;
import com.example.demo.api.dto.PostDto;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.post.comment.Comment;
import com.example.demo.api.dto.CommentDto;
import com.example.demo.domain.post.comment.CommentRepository;
import com.example.demo.domain.user.User;
import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    UserDto userdto;
    User user;
    PostDto postdto;
    Post post;
    @BeforeEach
    public void init(){
        userdto = createUser();
        Long uid = userService.register(userdto);
        user = userRepository.getReferenceById(uid);

        postdto = createPost();
        Long pid = postService.save(uid, postdto);
        post = postRepository.getReferenceById(pid);
    }
    @AfterEach
    public void clearDB(){
        commentRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void ?????????????????????(){
        //given
        String content = "????????? ????????????";

        CommentDto commentDto = createComment(content);

        //when
        Long id = commentService.save(post.getId(), commentDto);

        //then
        Comment comment = commentRepository.getReferenceById(id);

        assertThat(comment.getPost()).isEqualTo(post);
        assertThat(comment.getUser()).isEqualTo(user);

        assertThat(user.getComments()).contains(comment);
        assertThat(post.getComments()).contains(comment);

        assertThat(comment.getContent()).isEqualTo(content);
    }

    @Test
    public void ?????????????????????(){
        //given
        String content = "????????? ????????????";

        CommentDto commentDto = createComment(content);

        Long id = commentService.save(post.getId(), commentDto);

        String newContent = "????????? ????????? ?????? ??????";
        
        CommentDto newCommentDto = createComment(newContent);

        commentService.update(id, newCommentDto);

        //then
        Comment comment = commentRepository.getReferenceById(id);

        assertThat(comment.getContent()).isEqualTo(newContent);
    }

    @Test
    public void ?????????????????????(){
        //given
        String content = "????????? ????????????";

        CommentDto commentDto = createComment(content);

        Long id = commentService.save(post.getId(), commentDto);

        //then
        assertThat(commentRepository.findAll()).isNotEmpty();
        assertThat(user.getComments()).isNotEmpty();
        assertThat(post.getComments()).isNotEmpty();

        //when
        commentService.delete(id);

        //then
        assertThat(commentRepository.findAll()).isEmpty();
        assertThat(user.getComments()).isEmpty();
        assertThat(post.getComments()).isEmpty();
    }

    UserDto createUser() {
        UserDto dto = new UserDto();
        dto.setName("??????");
        dto.setPassword("??????");

        return dto;
    }
    PostDto createPost() {
        PostDto dto = new PostDto();
        dto.setTitle("??????");
        dto.setContent("??????");

        return dto;
    }
    CommentDto createComment(String content) {
        CommentDto dto =new CommentDto();
        dto.setContent(content);

        return dto;
    }
}
