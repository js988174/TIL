package com.spring.site.etc.token;

import com.spring.site.domain.Member;
import com.spring.site.etc.LoginSecurityService;
import com.spring.site.service.MemberService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Component
public class TokenProvider {

    private String secretKey = "secret";

    private long tokenValidTime = 1000L * 60 * 60;

    @Autowired
    private LoginSecurityService loginSecurityService;

    // 토큰 생성
    public String createToken(String member, String role) {
        Claims claims = Jwts.claims().setSubject(member); // 토큰에 저장되는 정보
        System.out.println(claims.toString());
        Member m = new Member();
        claims.put("role", role);
        Date now = new Date();
        return   Jwts.builder()
                .setHeaderParam("type", "token")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    // 토큰 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = loginSecurityService.loadUserByUsername(getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    // 토큰 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static String resolveToken(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                return cookie.getValue();
            }
        }
//        return request.getHeader("cookie");
        return null;
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        } catch (NullPointerException e) {
            System.out.println("token null");
        }
        return false;
    }


//    public boolean validateToken(String jwtToken) {
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//            return !claims.getBody().getExpiration().before(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//    }


}
