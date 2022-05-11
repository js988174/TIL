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


## JPA 시작히기
```
   EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
   EntityManager em = emf.createEntityManager();
   EntityTransaction tx = em.getTransaction();
```
* EntityManagerFactory
   1. JPA는 EntityManagerFactory를 만들어야 한다.
   2. EntityManager를 생성한다.
   
* EntityManager 
   1. 엔터티 매니저는 엔터티를 저장하는 메모리상의 데이터 베이스
   2. 커넥션풀을 이용해 쿼리를 날린다.
   
* EntityTransaction
   1. 조회를 제외한 데이터 변경하는 모든 작업은 트랜잭션 안에서 이루어져야 한다.
    
* JPQL
    1. 객체지향 SQL이다
    2. 엔터티 객체를 대상으로 쿼리
    
    
## 영속성 컨텍스트
### 엔터티를 영구 저장하는 환경

### 영속 상태란?
영속 컨텍스트에 해당 엔터티가 들어가 있다는 뜻

* 비영속
  
  영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
* 영속
  
  영속성 컨텍스트에 관리되는 상태
* 준영속
  
  영속성 컨텍스트에 저장되었다가 분리된 상태
* 삭제

    삭제된 상태

**만약 영속되어 있는 DB에서 조회하려 할 경우 DB에 select를 하는 것이 아닌 영속성 컨텍스트에서
객체를 리턴해준다.**  