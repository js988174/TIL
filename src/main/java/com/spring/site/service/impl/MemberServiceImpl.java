package com.spring.site.service.impl;



import com.spring.site.domain.Member;
import com.spring.site.mapper.MemberMapper;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;

    @Override
    public int add(Member member) throws Exception {
        return memberMapper.insert(member);
    }

    @Override
    public List<Member> list() throws Exception {
        System.out.println(memberMapper.AllList().get(0).getName());
        return memberMapper.AllList();
    }



}
