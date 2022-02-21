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
    MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberMapper.selectOne(userId);
        if (member == null){
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }
        return new LoginSecurity(member);
    }


}
