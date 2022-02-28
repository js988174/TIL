package com.spring.site.etc.token;



import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        ErrorCoded errorCoded;

        if (exception == null) {
            errorCoded = ErrorCoded.UNAUTHORIZEDException;
            setResponse(response, errorCoded);
        }

        if (exception.equals("ExpiredJwtException")) {
            errorCoded = ErrorCoded.ExpiredJwtException;
            setResponse(response, errorCoded);
        }
    }


    private void setResponse(HttpServletResponse response, ErrorCoded errorCoded) throws IOException {
        JSONObject json = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        json.put("code", errorCoded.getCode());
        json.put("message", errorCoded.getMessage());
        response.getWriter().print(json);
    }
}
