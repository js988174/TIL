package com.spring.site.web;

import com.spring.site.domain.Member;
import com.spring.site.etc.token.TokenProvider;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    ServletContext sc;
    @Autowired
    MemberService memberService;
    @Autowired
    TokenProvider jwtToken;


    @PostMapping("/login")
    public String loginCheck(Member member) throws Exception {
        System.out.println("로그인" + member);
        System.out.println("로그인컨트롤러");

//        String token = jwtToken.createToken(member.getId(), member.getRoles());
//        System.out.println(token);
        return "/";
    }

    @GetMapping("/loginForm")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model, @RequestBody Map<String, String> member) throws Exception {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        System.out.println("로그인폼");
        return "loginForm";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("member", new Member());
        return "member/createMemberForm";
    }

    @PostMapping("/add")
    public String add(@Valid Member member, BindingResult bindingResult, Errors errors, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", member);
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        System.out.println("로그아웃 성공");
        return "redirect:/";
    }

}
