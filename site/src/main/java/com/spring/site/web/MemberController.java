package com.spring.site.web;


import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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
    ServletContext sc;

    public MemberController(MemberService memberService) {

    }

    @GetMapping("/add")
    public String form()  {
        return "member/createMemberForm";
    }

    @PostMapping("add")
    public String add(Member m) throws Exception {
        memberService.add(m);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Member> list = memberService.list();
        System.out.println("g2");
        model.addAttribute("list", list);
        return "member/memberList";
    }


}
