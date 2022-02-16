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
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ServletContext sc;

    public MemberController(MemberService memberService) {

    }

    @GetMapping("/add")
    public String form()  {
        return "member/createMemberForm";
    }

    @PostMapping("/add")
    public String add(Member m) throws Exception {
        Member member = new Member();
        member.setName(m.getName());
        member.setId(m.getId());
        member.setPw(m.getPw());
        memberService.add(member);
        System.out.println(member.toString());

        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Member> list = memberService.list();
        model.addAttribute("list", list);
        return "member/memberList";
    }

    @PostMapping("/login")
    public String login(Member member) throws Exception {
        System.out.println("member.getPw() ==== " + member.getPw());
        System.out.println("passwordEncoder.encode(member.getPw()) === " + passwordEncoder.encode(member.getPw()));
        member.setPw(passwordEncoder.encode(member.getPw()));
        return "login";
    }


}
