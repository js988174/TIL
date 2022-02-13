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
@RequestMapping("/member")
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
        String uploadDir = sc.getRealPath("/upload");

        if (photoFile.getSize() > 0) {
            String filename = UUID.randomUUID().toString();
            photoFile.write(uploadDir + "/" + filename);
            m.setPicture(filename);

            Thumbnails.of(uploadDir + "/" + filename)
                    .size(30, 30)
                    .outputFormat("jpg")
                    .crop(Positions.CENTER)
                    .toFiles(new Rename() {

                        @Override
                        public String apply(String name, ThumbnailParameter param) {
                            return name + "_30x30";
                        }
                    });

            Thumbnails.of(uploadDir + "/" + filename)
                    .size(110, 110)
                    .outputFormat("jpg")
                    .crop(Positions.CENTER)
                    .toFiles(new Rename() {

                        @Override
                        public String apply(String name, ThumbnailParameter param) {
                            return name + "_110x110";
                        }
                    });
        }

        memberService.add(m);
        return "redirect:list";

    }

    @GetMapping("delete")
    public String delete(int no) throws Exception {
        Member member = memberService.get(no);
        if (member == null) {
            throw new Exception("해당 번호의 회원이 없습니다.");
        }

<<<<<<< Updated upstream
        memberService.delete(no);

        return "redirect:list";
    }

    @GetMapping("detail")
    public void detail(int no, Model model) throws Exception {
        Member m = memberService.get(no);
        model.addAttribute("member", m);
    }

    @GetMapping("list")
    public void list(String keyword, Model model) throws Exception {
        List<Member> list = memberService.list(keyword);
=======
    @GetMapping("list")
    public String list(Model model) throws Exception {
        List<Member> list = memberService.list();
>>>>>>> Stashed changes
        model.addAttribute("list", list);
        return "list.html";
    }

    @PostMapping("update")
    public String update(Member m, Part photoFile) throws Exception {

        String uploadDir = sc.getRealPath("/upload");
        if (photoFile.getSize() > 0) {
            String filename = UUID.randomUUID().toString();
            photoFile.write(uploadDir + "/" + filename);
            m.setPicture(filename);

            Thumbnails.of(uploadDir + "/" + filename)
                    .size(110, 110)
                    .outputFormat("jpg")
                    .crop(Positions.CENTER)
                    .toFiles(new Rename() {

                        @Override
                        public String apply(String name, ThumbnailParameter param) {
                            return name + "_110x110";
                        }
                    });
        }

        if (memberService.update(m) == 0) {
            throw new Exception("해당 번호의 회원이 없습니다.");
        }
        return "redirect:list";
    }
}
