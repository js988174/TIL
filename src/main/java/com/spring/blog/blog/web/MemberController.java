package com.spring.blog.blog.web;


import com.spring.blog.blog.domain.Member;
import com.spring.blog.blog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.List;

@Controller
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    MemberService memberService;
    ServletContext sc;

    @Autowired
    public MemberController(MemberService memberService, ServletContext sc) {
        this.memberService = memberService;
        this.sc = sc;
    }

    @GetMapping("/member/add")
    public String form()  {
        return "member/form";
    }

    @PostMapping("member/add")
    public String add(Member m) throws Exception {
        memberService.add(m);
        return "redirect:/";
    }


    @GetMapping("/member/list")
    public void list(Model model) throws Exception {
        List<Member> list = memberService.list();
        model.addAttribute("list", list);
    }


}
