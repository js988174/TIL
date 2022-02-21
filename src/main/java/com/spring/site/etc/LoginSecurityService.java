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
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Member member = new Member();
        member.setId(userName);
        System.out.println("loadUserByUsername");
        member = memberService.oneSelect(member);
               if (member != null) {
                  return new LoginSecurity(member);
               }

            return null;
    }


}
