# jpa 프로그래밍 강의 
## [강의주소](https://www.inflearn.com/course/ORM-JPA-Basic)

## JPA란
1. Java Persistence API
2. 자바 진영의 ORM 기술 표준이다

## ORM이란

1. Object-relational mapping
2. 객체는 객체대로 관계형 데이터베이스는 데이터베이스대로 설계

## JPA를 사용해야하는 이유

1. 유지보수 측면
    - Mybatis를 사용할 시 쿼리문을 일일이 다 작성해야하고 중간에 필드가 추가될 시
      모든 SQL을 수정해야합니다.
      
    - jpa를 사용할 시 필드만 추가하면 SQL은 JPA가 처리해줍니다.
      
2. 패러다임의 불일치
```
public class BoardDto {
    private int no;
    private String title;
    private String content;
    private int writer;
}
public class MemberDto {
    private int mNo;
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{2,9}",
            message = "아이디는 영문, 숫자만 가능하며 2 ~ 10자리까지 가능합니다.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,16}",
            message = "비밀번호는 영문과 숫자 조합으로 6 ~ 16자리까지 가능합니다.")
    private String pw;
    private String name;
    private String email;
    private String phone;
}
```
- BoardDto에서 MemberDto의 정보를 받기 위해 int형으로 만들었지만 
MYbatis에서는 이것이 되지 않았다.
```
public class BoardDto {
    private int no;
    private String title;
    private String content;
    private MemberDto writer;
}
```
- 이런식으로 BoardDto를 수정했지만 이렇게 작성할 시 쿼리의 수가 많아지고 복잡해지기만 했다.
**JAP는 이런 패러다임의 불일치 문제들을 완화 신켜주며 SQL 의존적인 코딩들 쉽게 풀어주었다.**  

