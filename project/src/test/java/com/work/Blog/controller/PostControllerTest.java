package com.work.Blog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다.")
    void test() throws Exception {
        // expected
        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "글 제목입니다.")
                .param("content", "글 내용입니다."))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andDo(print());

    }


    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception {
        // expected
        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"\", \"content\": \"내용입니다.\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());

    }

    @Test
    @DisplayName("/posts 요청시 DB값 저장")
    void test3() throws Exception {
        // expected
        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"\", \"content\": \"내용입니다.\"}")
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());

    }

}