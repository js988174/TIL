package com.spring.site.etc;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.crypto.Data;
import java.time.Duration;
import java.util.Date;

public class Token {

    private String type = "Bearer";

    public String JwtToken() {
        Date now = new Date();
        System.out.println("token");

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

}
