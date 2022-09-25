package com.work.Blog.controller;


import com.work.Blog.domain.Post;
import com.work.Blog.request.PostCreate;
import com.work.Blog.response.PostResponse;
import com.work.Blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Post post(@RequestBody @Valid PostCreate request) throws Exception {
        return postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long postId) {
        PostResponse response = postService.get(postId);
        return response;
    }

    // 조회 API 여러개의 글 조회
    @GetMapping("/posts/list")
    public List<PostResponse> getList() {
        return postService.getList(1);
    }

}
