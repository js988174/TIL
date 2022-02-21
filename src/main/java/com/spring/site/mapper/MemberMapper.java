package com.spring.site.mapper;


import com.spring.site.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MemberMapper {

    @Options(useGeneratedKeys = true, keyProperty = "no")
    @Insert("INSERT INTO  member(id, pw, name) VALUES(#{id}, #{pw},#{name})")
    int insert(Member member) throws Exception;
    @Select("SELECT * FROM member")
    List<Member> allList() throws Exception;
    @Select("SELECT * FROM member where id = #{id}")
    Member selectOne(String userId);

}
