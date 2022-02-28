//package com.spring.site.etc.token;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spring.site.domain.Member;
//import com.spring.site.etc.LoginSecurity;
//import com.spring.site.etc.LoginSecurityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private long tokenValidTime = 1000L * 60 * 60;
//    private String secretKey = "secret";
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        System.out.println("JWAutenFilter: 로그인 시도중");
//
//
//        try {
//            ObjectMapper om = new ObjectMapper();
//            Member member = om.readValue(request.getInputStream(), Member.class);
//            System.out.println(member);
//
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(member.getId(), member.getPw());
//
//            // LoginSecurityService의 loadUserByUsername()함수 실행
//            Authentication authentication =
//                    authenticationManager.authenticate(authenticationToken);
//
//            LoginSecurity loginSecurity = (LoginSecurity) authentication.getPrincipal();
//            System.out.println("로그인 완료: " + loginSecurity.getMember().getId());
//
//            // jwt 토큰 하면서 세션을 만들 이유가 없지만 만드는 이유
//            // => 권한 처리 떔에  하는거
//
//
//            return authentication;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        System.out.println("successfulAuth 실행");
//        LoginSecurity loginSecurity = (LoginSecurity) authResult.getPrincipal();
//
//        String jwtToken = JWT.create()
//                .withSubject(loginSecurity.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + tokenValidTime))
//                .withClaim("id", loginSecurity.getMember().getId())
//                .withClaim("pw", loginSecurity.getMember().getPw())
//                .sign(Algorithm.HMAC512(secretKey));
//
//        response.addHeader("Authorization", "Bearer"+jwtToken);
//    }
//
//
//}
