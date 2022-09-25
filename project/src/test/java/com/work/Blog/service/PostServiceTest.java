package com.work.Blog.service;

import com.work.Blog.domain.Post;
import com.work.Blog.repository.PostRepository;
import com.work.Blog.request.PostCreate;
import com.work.Blog.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given

        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다.")
                .build();


        // when
        postService.write(postCreate);

        // then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        // given
        Post post = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(post);

        Long postId = 1L;

        // when
        PostResponse response = postService.get(post.getId());


        // then
        Assertions.assertNotNull(response);
        assertEquals("foo.", post.getTitle());
        assertEquals("bar.", post.getContent());

    }


    @Test
    @DisplayName("글 여러개 조회")
    void test3() {
        // given
        Post post1 = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(post1);

        Post post2 = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(post2);


        // when
        List<PostResponse> posts = postService.getList();

        // then
        assertEquals(2L, posts.size());

    }
}