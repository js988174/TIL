package com.spring.site.service;


import com.spring.site.domain.Member;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

public interface MemberService {

    int add(Member member) throws Exception;

    List<Member> list() throws Exception;

    Member oneSelect(Member member);


}
