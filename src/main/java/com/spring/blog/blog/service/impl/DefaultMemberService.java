package com.spring.blog.blog.service.impl;


import com.spring.blog.blog.dao.MemberDao;
import com.spring.blog.blog.domain.Member;
import com.spring.blog.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

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
