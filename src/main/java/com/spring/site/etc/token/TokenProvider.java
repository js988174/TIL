package com.spring.site.etc.token;

import com.spring.site.domain.Member;
import com.spring.site.etc.LoginSecurityService;
import com.spring.site.service.MemberService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
@Component
public class TokenProvider {

    private String secretKey = "secret";

    private long tokenValidTime = 1000L * 60 * 60;



    @Autowired
    private LoginSecurityService loginSecurityService;

    // 토큰 생성
    public String createToken(String member, String role) {
        Claims claims = Jwts.claims().setSubject(member); // JWT payload 에 저장되는 정보단위
        claims.put("roles", role); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        System.out.println("member "+member);
        System.out.println("member role"+role);
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

    }

    // 토큰 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = loginSecurityService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }




    // 토큰 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    // 헤더를 통해 token값 가져오기
    public static String resolveToken(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = (String)headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " : " + value + "<br>");
        }
        System.out.println("헤더전체");
        System.out.println("request:"+request.getHeader("cookie"));
        return request.getHeader("cookie");
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
