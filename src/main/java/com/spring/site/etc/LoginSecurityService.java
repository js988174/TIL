package com.spring.site.etc;

import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginSecurityService implements UserDetailsService {
    @Autowired
    MemberService memberService;


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = new Member();
        member.setId(id);
        member = memberService.oneSelect(member);
        System.out.println("loadUserByUsername");
        if(member !=null){
            System.out.println("로그인 성공");
            return new LoginSecurity(member);
        }
        return null;
    }
}
