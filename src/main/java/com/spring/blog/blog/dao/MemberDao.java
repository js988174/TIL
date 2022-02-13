package com.spring.blog.blog.dao;


import com.spring.blog.blog.domain.Member;

import java.util.List;

public interface MemberDao {
    int insert(Member member) throws Exception;

    List<Member> findByKeyword(String keyword) throws Exception;

    Member findByEmail(String email) throws Exception;

    Member findByNo(int no) throws Exception;

    int update(Member member) throws Exception;

    int delete(int no) throws Exception;
}
