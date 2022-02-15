package com.spring.site.service.impl;


import com.spring.site.dao.MemberDao;
import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public int add(Member member) throws Exception {
        return memberDao.insert(member);
    }

    @Override
    public List<Member> list() throws Exception {
        System.out.println(memberDao.AllList().get(0).getName());
        return memberDao.AllList();
    }

}
