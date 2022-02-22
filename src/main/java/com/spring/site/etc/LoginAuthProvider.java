package com.spring.site.etc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class LoginAuthProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();// 아이디
        System.out.println("authentication.getName() = " + authentication.getName());

        String pw = authentication.getCredentials().toString();//비밀번호
        System.out.println("(String) authentication.getCredentials()= " + passwordEncoder.encode(pw));

        LoginSecurity user= (LoginSecurity) userDetailsService.loadUserByUsername(id);
        System.out.println("비밀번호 체크");
        System.out.println("user.getPassword() =" + user.getPassword() );

        if(!passwordEncoder.matches(user.getPassword(),pw)) {
            System.out.println("비밀번호 에러");
            throw new BadCredentialsException("비밀번호 불일치");
        }

        System.out.println("로그인 제대로 성공");
        return new UsernamePasswordAuthenticationToken(id, pw, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
