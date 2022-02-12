package com.spring.blog.dao;


import com.spring.blog.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {
    int insert(Member member) throws Exception;

    List<Member> AllList() throws Exception;

}
