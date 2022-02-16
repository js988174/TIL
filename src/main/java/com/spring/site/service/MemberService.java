package com.spring.site.service;


import com.spring.site.domain.Member;

import java.util.List;

public interface MemberService {

    int add(Member member) throws Exception;

    List<Member> list() throws Exception;


}
