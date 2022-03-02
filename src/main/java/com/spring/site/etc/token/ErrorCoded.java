package com.spring.site.etc.token;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCoded {

    UsernameOrPasswordNotFoundExeption (400, "하이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    ForbiddenException(403, "해당 요청에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZEDException(401, "로그인 후 이용가능합니다", HttpStatus.UNAUTHORIZED),
    ExpiredJwtException(444, "기존 토큰이 만료되었습니다", HttpStatus.UNAUTHORIZED),
    ReLogin(445, "토큰이 만료되었습니다, 다시 로그인 해주세요", HttpStatus.UNAUTHORIZED),
    ;

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private HttpStatus status;

    ErrorCoded(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
