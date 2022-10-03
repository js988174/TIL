package com.work.Blog.repository;

import com.work.Blog.domain.Post;
import com.work.Blog.request.PostSearch;

import java.util.List;


public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
