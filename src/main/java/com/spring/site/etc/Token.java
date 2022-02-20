package com.spring.site.etc;

import com.spring.site.domain.Member;
import com.spring.site.mapper.MemberMapper;
import com.spring.site.service.MemberService;
import com.spring.site.service.impl.MemberServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Token {

    private String secretKey = "siteKey";
    private MemberService memberService;

    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 정보
    public static String JwtToken(String member) {

        Date now = new Date();
        return   Jwts.builder()
                .setSubject(member)
                .setIssuer("token")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofHours(1).toMillis()))
                .claim("id", "id")
                .claim("pw", "pw")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token)
                .getBody();
                return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

}
