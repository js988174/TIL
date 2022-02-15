package com.spring.site.dao;



import com.spring.site.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


import java.util.List;


@Mapper
public interface MemberDao {
    @Insert("INSERT INTO member(id, pw, name) VALUES(#{id}, #{pw}, #{name}")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    int insert(Member member) throws Exception;
    @Select("SELECT * FROM member")
    List<Member> AllList() throws Exception;

}
