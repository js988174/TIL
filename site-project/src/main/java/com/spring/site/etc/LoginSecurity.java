package com.spring.site.etc;

import com.spring.site.domain.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 시큐리티가 로그인 주소에서 낚아챔
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어줍니다
// 오브젝트타입 => Authentication
//Authentlcation 안에 user정보가있어야됨
//User 오브젝트 타입 = > UserDetails 타입객체
//Security Session => Authentikation => UserDetails

@Data
public class LoginSecurity implements UserDetails {

    private Member member;

    public LoginSecurity(Member member){
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    //유저 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("GrantedAuthority");
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPw();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
