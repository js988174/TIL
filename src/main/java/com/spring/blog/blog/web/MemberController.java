package com.spring.blog.blog.web;


import com.spring.blog.blog.domain.Member;
import com.spring.blog.blog.service.MemberService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/member/")
public class MemberController {

    MemberService memberService;
    ServletContext sc;

    public MemberController(MemberService memberService, ServletContext sc) {
        this.memberService = memberService;
        this.sc = sc;
    }

    @GetMapping("form")
    public void form() throws Exception {

    }

    @PostMapping("add")
    public String add(Member m, Part photoFile) throws Exception {
        memberService.add(m);
        return "redirect:list";

    }


    @GetMapping("list")
    public void list(Model model) throws Exception {
        List<Member> list = memberService.list();
        model.addAttribute("list", list);
    }


}
