package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.post.comment.Comment;
import com.example.demo.api.dto.CommentDto;
import com.example.demo.domain.post.comment.CommentRepository;
import com.example.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long save(Long postId, CommentDto commentDto){
        Post post = postRepository.getReferenceById(postId);
        User user = post.getUser();

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .build();

        user.addComment(comment);
        post.addComment(comment);

        return commentRepository.save(comment).getId();
    }
    public Long update(Long id, CommentDto commentDto){
        Comment comment = commentRepository.getReferenceById(id);

        comment.changeComment(commentDto.getContent());

        return id;
    }
    public Long delete(Long id){
        Comment comment = commentRepository.getReferenceById(id);

        comment.getPost().deleteComment(comment);
        comment.getUser().deleteComment(comment);

        commentRepository.deleteById(id);

        return id;
    }
}
