# jpashop
# 실전 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발 강의 1

## H2 DB 설정
H2 TCP 설정을 Spring boot와 연결하하는 과정이다.
주의사항: H2 1.4.200 버전부터 MVCC 옵션이 제거되어서
url에서 MVCC=true를 지워줘야 한다.
```
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/jpashop
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        #        show_sql: true
        format_sql: true

logging.level:
  org.hibernate.SQL: debug

#  org.hibernate.type: trace
```


## DB 모델링
<img src="https://user-images.githubusercontent.com/76925402/170308759-18b3d135-46bd-4541-964d-4686ea1e3fed.png" width="700">

## 기술 팁 
* 실무에서는 무조권 지연 로딩으로 설정 (OneToOne, ManyToOne) -> JPQL 실행시 n + 1 문제 많이 발생
* 가급적 Setter 사용x (유지보수가 어려움)

## API 개발
```
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
```

이런식으로 엔티티를 노출시키면 나중에 엔티티가 변할떄 문제가 발생한다.


```
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
```

CreateMemberRequest란 메서드를 만들어서 로직을 분리시킨다.

* Byte