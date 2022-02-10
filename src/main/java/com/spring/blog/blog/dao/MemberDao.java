package com.spring.blog.blog.dao;

import com.spring.blog.blog.domain.Member;

import java.util.List;

public interface MemberDao {
    int insert(Member member) throws Exception;

    List<Member> AllList() throws Exception;


}
