package com.spring.blog.service;


import com.spring.blog.domain.Member;

import java.util.List;

public interface MemberService {


    int add(Member member) throws Exception;

    List<Member> list() throws Exception;


}
