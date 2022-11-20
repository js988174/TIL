package com.work.Blog.controller;


import com.work.Blog.exception.Exception;
import com.work.Blog.exception.InvalidRequest;
import com.work.Blog.exception.PostNotFound;
import com.work.Blog.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
            ErrorResponse response = ErrorResponse.builder()
                    .code("400")
                    .message("잘못된 요청입니다.")
                    .build();
//            FieldError fieldError = e.getFieldError();
//            String field = fieldError.getField();
//            String message = fieldError.getDefaultMessage();

          for (FieldError fieldError : e.getFieldErrors()) {
              response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
          }

          return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFound.class)
    public ResponseEntity<ErrorResponse> Exception(Exception e) {
        int statusCode = e.statusCode();

        ErrorResponse response = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();


        ResponseEntity<ErrorResponse> errorResponse = ResponseEntity.status(statusCode)
                .body(response);

        return errorResponse;
    }
}
