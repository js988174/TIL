package com.spring.blog.blog.dao;

import com.spring.blog.blog.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface MemberDao {
    int insert(Member member) throws Exception;

    List<Member> AllList() throws Exception;

}
