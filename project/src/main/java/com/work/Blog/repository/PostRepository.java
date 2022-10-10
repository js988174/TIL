package com.work.Blog.repository;

import com.work.Blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
