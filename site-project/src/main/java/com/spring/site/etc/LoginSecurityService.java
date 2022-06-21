package com.spring.site.etc;

import com.spring.site.domain.Member;
import com.spring.site.mapper.MemberMapper;
import com.spring.site.service.MemberService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginSecurityService implements UserDetailsService {

    @Autowired
    MemberMapper MemberMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = new Member();
        member.setId(id);
        member = MemberMapper.selectOne(member);
        System.out.println("memberService.oneSelect(member" + MemberMapper.selectOne(member).toString());
        if(member !=null){
            System.out.println(id);
            System.out.println("로그인 성공");
            System.out.println(member);
            return new LoginSecurity(member);

        }

        return null;
    }

}
