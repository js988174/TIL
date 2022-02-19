package com.spring.site.etc;

import com.spring.site.mapper.MemberMapper;
import com.spring.site.service.MemberService;
import com.spring.site.service.impl.MemberServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

public class Token {

    private String type = "X-AUTH-TOKEN";
    private String secretKey = "webfirewood";

    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // 토큰 정보
    public String JwtToken() {
        Date now = new Date();

        return type + Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("token")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofHours(1).toMillis()))
                .claim("id", "id")
                .claim("pw", "pw")
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

//    // 토큰 조회
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = MemberMapper.(this.getUserPk(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }

    // 헤더에서 토큰값 가져오가
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰 유효성 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
