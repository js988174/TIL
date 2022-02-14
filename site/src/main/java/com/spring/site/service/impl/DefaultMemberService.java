package com.spring.site.service.impl;


import com.spring.site.dao.MemberDao;
import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {


    MemberDao memberDao;

    public DefaultMemberService() {

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
