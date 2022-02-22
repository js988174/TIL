package com.spring.site.web.filter;

import com.spring.site.domain.Member;
import com.spring.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class IdCheckFilter extends AbstractValidator<Member>{

    @Autowired
    private MemberService memberService;

    @Override
    protected void doValidate(Member member, Errors errors) {
        if (memberService.checkId(member.getId())) {
            errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다."); }
    }
}
