package com.work.Blog.service;

import com.work.Blog.domain.Post;
import com.work.Blog.exception.PostNotFound;
import com.work.Blog.repository.PostRepository;
import com.work.Blog.request.PostCreate;
import com.work.Blog.request.PostEdit;
import com.work.Blog.request.PostSearch;
import com.work.Blog.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.DESC;

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
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목 - " + i)
                        .content("내용 - " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

//        Pageable pageable = PageRequest.of(0, 5, DESC, "id");

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();


        // when
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        assertEquals(10L, posts.size());
        assertEquals("제목 - 9", posts.get(9).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        Assertions.assertEquals("제목 수정", changePost.getTitle());

    }

    @Test
    @DisplayName("글 내용 수정")
    void test5() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        Assertions.assertEquals("제목 수정", changePost.getContent());

    }

    @Test
    @DisplayName("글 내용 수정")
    void test6() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("제목 수정")
                .content("내용 수정")
                .build();

        // when
        postService.edit(post.getId(), postEdit);

        // then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));

        Assertions.assertEquals("제목 수정", changePost.getTitle());
        Assertions.assertEquals("내용 수정", changePost.getContent());
    }

    @Test
    @DisplayName("글 삭제")
    void test7() {
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        postService.delete(post.getId());

        Assertions.assertEquals(0,postRepository.count());
    }

    @Test
    @DisplayName("글 조회")
    void test8() {
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        postRepository.save(post);

        PostResponse response = postService.get(2L);

      Assertions.assertThrows(PostNotFound.class, () -> {
          postService.get(post.getId() + 1L);
       });

    }
}