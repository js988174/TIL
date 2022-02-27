package com.spring.site.etc.token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.site.domain.Member;
import com.spring.site.etc.LoginSecurity;
import com.spring.site.etc.LoginSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JWAutenFilter: 로그인 시도중");


        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            System.out.println(member);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(member.getId(), member.getPw());

            // LoginSecurityService의 loadUserByUsername()함수 실행
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            LoginSecurity loginSecurity = (LoginSecurity) authentication.getPrincipal();
            System.out.println("로그인 완료: " + loginSecurity.getMember().getId());

            // jwt 토큰 하면서 세션을 만들 이유가 없지만 만드닌 이유
            // => 권한 처리 떔에  하는거


            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
