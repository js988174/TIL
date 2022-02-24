package com.spring.site.web;


import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletContext;


@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    ServletContext sc;

    public MemberController(MemberService memberService) {

    }

    @GetMapping("/detail")
    public String detail(Model model,Member member) throws Exception {
        model.addAttribute(member);
        return "member/memberList";
    }


}
