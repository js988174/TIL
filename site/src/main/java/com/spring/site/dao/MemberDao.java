package com.spring.site.dao;



import com.spring.site.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;


@Mapper
public interface MemberDao {
    int insert(Member member) throws Exception;
    @Select("SELECT * FROM member")
    List<Member> AllList() throws Exception;

}
