package com.spring.blog.blog.service;


import com.spring.blog.blog.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
public interface MemberService {


    int add(Member member) throws Exception;

    List<Member> list() throws Exception;


}
