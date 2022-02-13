package com.spring.blog.blog.service.impl;


import com.spring.blog.blog.dao.MemberDao;
import com.spring.blog.blog.domain.Member;
import com.spring.blog.blog.service.MemberService;

import java.util.List;

public class DefaultMemberService implements MemberService {

    MemberDao memberDao;

    public DefaultMemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public int add(Member member) throws Exception {
        return memberDao.insert(member);
    }

    @Override
    public List<Member> list(String keyword) throws Exception {
        return memberDao.findByKeyword(keyword);
    }

    @Override
    public Member get(int no) throws Exception {
        return memberDao.findByNo(no);
    }

    @Override
    public Member getEmail(String email) throws Exception {
        return memberDao.findByEmail(email);
    }

    @Override
    public int update(Member member) throws Exception {
        return memberDao.update(member);
    }

    @Override
    public int delete(int no) throws Exception {
        return memberDao.delete(no);
    }
}
