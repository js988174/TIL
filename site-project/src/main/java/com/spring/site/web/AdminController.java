package com.spring.site.web;

import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    MemberService memberService;

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Member> list = memberService.list();
        model.addAttribute("list", list);
        return "/admin/memberList";
    }

}
