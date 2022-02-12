package com.spring.blog.service.impl;

import com.spring.blog.dao.MemberDao;
import com.spring.blog.domain.Member;
import com.spring.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {

    @Autowired
    MemberDao memberDao;

    public DefaultMemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }


    @Override
    public int add(Member member) throws Exception {
        return memberDao.insert(member);
    }

    @Override
    public List<Member> list() throws Exception {
        return memberDao.AllList();
    }

}
