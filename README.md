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
   1. 조회를 제외한 데이터 변경하는 모든 작업은 트랜잭션 안에서 이루어진다.
    
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
* 준영속(detach)
  
  영속성 컨텍스트에 저장되었다가 분리된 상태 
  이렇게 되면 영속성 컨텍스트에서 제공 하는 기능 사용x
* 삭제

    삭제된 상태

* 플러시

    변경감지, 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송

**만약 영속되어 있는 DB에서 조회하려 할 경우 DB에 select를 하는 것이 아닌 영속성 컨텍스트에서
객체를 리턴해준다.**  


## 엔티티 매핑

### 어노테이션과 컬럼
* @Entity : JPA를 사용할수 있게 해주는 것
* @Table: 키를 생성하는 테이블을 만듬
* @Column : 테이블 안 컬럼과 매칭 제약조건을 걸수 있다. (컬럼에는 유니크키를 잘 안건다.)
* @Id : 기본 키 설정
* @Enumerated : enum 타입(ORDINAL를 쓰면 순서에 문제가 생길수 있어서 String을 써야한다 )
* @Temporal : 날짜 타입의 컬럼
* @Lob : BLOB(byte), CLOB(String, char[]) 매핑
* @Transient : 컬럼에 매핑하지 않음(임시로 보관하고 싶을 때 사용)

* name : 필드를 매핑할 테이블의 컬럼 이름
* insertable, updateable : 등록 가능 , 변경 가능
* nullable : null값 설정
* unique : 유니크 제약 조건
* columnDefinition : 데이터베이스 컬럼 정보 직접 입력 가능하게 해줌 (varchar100)
* length : 문자 길이 제약조건
* precision(전체 자릿수),scale(소수 자릿수) : BigDecimal 타입에서 사용

## 데이터베이스 스키마 자동 생성

* create : 기존테이블 삭제 후 다시 생성 
* create - drop : create 기능에서 종료할때 테이블 drop
* update : 변경한것 반영
* validate : 엔티티와 테이블이 정상 매핑되었는지 확인
* none : 사용x

### 장점
1. DDL을 애플리케이션 실행 시점에서 자동 생성 (DDL : 데이터 정의어)
2. 테이블 중심이 아닌 객체 중심
3. 데이터베이스에 맞는 적절한 DDL 생성

### 주의할점
**운영 장비에는 create, create-drop, update 사용 금지**

## 연관관계 매핑
1. 객체의 양방향 관계는 양방향 관계가 아닌 서로 다른 단반향 관계 2개이다.
2. 연관관계의 주인만이 외래 키를 관리
3. 양방향 매핑시 연관관계의 주인에 값을 입력해야한다.

ex) 양방향 
```
@Entity 
@GET
@SET
public class Post { 
@Id @GeneratedValue 
@Column(name = "POST_ID")
private Long id; 
@Column(name = "TITLE")
private String title;
 @ManyToOne @JoinColumn(name = "BOARD_ID")
private Board board; 
} 

@Entity
@GET
@SET
public class Board{
@Id @GeneratedValue 
private Long id; 
private String title; 
@OneToMany(mappedBy = "board") 
List<Post> posts = new ArrayList<>(); 
  }
```
3. 이런식으로 mapperBy 속성으로 주인 지정 (없는 쪽이 주인)

## 상속관계 매핑
### 슈퍼타입과 서브타입 
* 공통적인 컬럼을 묶는 방법
  <img src="https://desert-elk-95d.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F3f2ab4e9-4c3b-4d91-9a59-471583ce849b%2FUntitled.png?table=block&id=44565e1a-ad49-48ec-8a9e-3aea3e1daad6&spaceId=04134d59-90bb-48a2-b600-8335846e6312&width=2000&userId=&cache=v2"  width="700" height="370">
  + 이런식으로 만들었을떄 item을 부모 객체로 나머지를 자식 클래스로 만드는것이다.
  
* @Inheritance : 부모 객체에 사용하는 어노테이션이고 3가지 속성이 있다.
  + JOINED: Item 테이블에서 하위테이블과 조인할 때 사용
  + SINGLE_TABLE: 자식 엔티티를 테이블로 사용하지 않고 Item 테이블에 모든 컬럼을 넣을 때 사용(단일 테이블)
  + TABLE_PER_CLASS: 부모 객체를 테이블로 사용하지 않고 부모 객체의 멤버변수를 모두 각 테이블마다 사용

* @DiscriminatorColumn : 하위 클래스를 구분해주는 용도로 사용한다.
* @MappedSuperclass : 엔티티 공통으로 사용하는 매핑 정보 모으는 역활 
## JPA 하면서 생겼던 오류 
1. entity 이름을 order로 할시 오류 발생
2. maven -> gradle로 변경시 persistence.xml에 class를 추가해줘야 한다.