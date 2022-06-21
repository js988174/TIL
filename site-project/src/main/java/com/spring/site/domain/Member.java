package com.spring.site.domain;


import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class Member {

    private int no;
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{2,9}",
            message = "아이디는 영문, 숫자만 가능하며 2 ~ 10자리까지 가능합니다.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,16}",
            message = "비밀번호는 영문과 숫자 조합으로 6 ~ 16자리까지 가능합니다.")
    private String pw;
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    private String name;
    private String role = "ROLE_ADMIN";
    private List<String> roles = new ArrayList<>();

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Member{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", roles=" + roles +
                '}';
    }

    public Member(int no, String id, String pw, String name, String role, List<String> roles) {
        this.no = no;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.role = role;
        this.roles = roles;
    }


}
