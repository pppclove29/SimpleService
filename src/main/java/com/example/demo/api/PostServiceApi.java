package com.example.demo.api;

import com.example.demo.api.dto.PostDto;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostServiceApi {

    public final PostService postService;

    @GetMapping
    public List<PostDto> showPosts() {
        return postService.findAllPost();
    }

    @PostMapping
    public PostDto save(@RequestParam("userid") Long id,
                        @RequestBody PostDto postDto) {
        Long sid = postService.save(id, postDto);

        return postService.findPost(sid);
    }

    @PutMapping("/{id}")
    public List<PostDto> update(@RequestBody PostDto postDto,
                                @PathVariable Long id) {
        List<PostDto> postDtos = new ArrayList<>();

        postDtos.add(postService.findPost(id));

        postService.update(id, postDto);

        postDtos.add(postService.findPost(id));

        return postDtos;
    }

    @DeleteMapping("/{id}")
    public List<PostDto> delete(@PathVariable Long id){
        postService.delete(id);

        return postService.findAllPost();
    }
}
