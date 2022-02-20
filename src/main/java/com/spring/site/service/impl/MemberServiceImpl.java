package com.spring.site.service.impl;



import com.spring.site.domain.Member;
import com.spring.site.mapper.MemberMapper;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public int add(Member member) throws Exception {
        System.out.println("서비스 임플리먼트 에드" + passwordEncoder.encode(member.getPw()).length());
        member.setPw(passwordEncoder.encode(member.getPw()));
        return memberMapper.insert(member);
    }

    @Override
    public List<Member> list() throws Exception {
        System.out.println(memberMapper.allList().get(0).getName());
        return memberMapper.allList();
    }

    @Override
    public Member oneSelect(Member member)   {

        return member ;
    }
}
