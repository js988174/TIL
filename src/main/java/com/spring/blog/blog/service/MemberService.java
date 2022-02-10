package com.spring.blog.blog.service;

import board.boardspring.domain.Member;

import java.util.List;

public interface MemberService {

    int add(Member member) throws Exception;

    List<Member> list(String keyword) throws Exception;

    Member get(int no) throws Exception;

    Member getEmail(String email) throws Exception;

    int update(Member member) throws Exception;

    int delete(int no) throws Exception;
}
