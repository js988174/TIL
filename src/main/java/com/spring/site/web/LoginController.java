package com.spring.site.web;

import com.spring.site.domain.Member;
import com.spring.site.etc.Token;
import com.spring.site.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    ServletContext sc;
    @Autowired
    MemberService memberService;
    @PostMapping("/login")
    public String loginCheck(Member member, HttpServletResponse response) throws Exception {
        System.out.println("로그인"+member);
        if(memberService.oneSelect(member)!=null) {
            String token = Token.JwtToken(member.getId());
            response.setHeader("X-AUTH-TOKEN", token);

            Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            response.addCookie(cookie);
            System.out.println(token);

            return "home";
        }

        return "loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() throws Exception {
        System.out.println("로그인폼");
        return "loginForm";
    }
    @GetMapping("/add")
    public String form(Model model)  {
        model.addAttribute("member", new Member());
        return "member/createMemberForm";
    }

    @PostMapping("/add")
    public String add(@Valid Member member, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "member/createMemberForm";
        }

        Member m = new Member();
        m.setId(member.getId());
        m.setPw((member.getPw()));
        m.setName(member.getName());
        System.out.println("add");
        memberService.add(m);

        return "redirect:/member/list";
    }
}
