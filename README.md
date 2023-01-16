# Spring-Security

* WebSecurityConfigurerAdapter : 웹 보안 기능 초기화 및 설정
* SecurityConfig: 사용자 정의 보안 설정 클래스
* HttpSecurity: 세부적인 보안 기능을 설정할 수 있는 API 제공

## 기능
* rememberMe: 저장 기능
* RememberMeService에서 토큰 쿠키 추출 -> 존재하지 않으면 chain.doFilter , 존재하면 Decode Token에서 유무 판단

* AnonymousAuthenticationFilter: 익명사용자 인증 처리 필터
* http.sessionManagement: 세션 관리 기능
  + maxSessionsPreventsLogin: 동시 로그인 차단
  + invalidSessionUrl: 세션이 유효하지 않을 때 이동 할 페이지
  + expiredUrl: 세션이 만료될 경우 이동 할 페이지

* authorizeRequest: 권한 설정
* ExceptionTranslationFilter: 인증 예외 처리

* http.csrf: 위조 방지 필터(기본 활성화)

## DelegatingFilterProxy
* 서블릿 필터는 스프링에서 정의된 빈을 주입해서 사용할 수 없음
* 특정한 이름을 가진 스프링 빈을 찾아 그 빈에게 요청을 위임
  + springSecurityFilterChain 이름으로 생성된 빈을 ApplicationContext 에서 찾아 요청을 위임
  + 보안처리 x

## Authentication 
* 사용자의 인증 정보를 저장하는 토큰 개념
  + principal: 사용자 아이디 혹은 User 객체를 저장
  + credentials: 사용자 비밀번호
  + authorities: 인증된 사용자의 권한 목록
  + details: 인증 부가 정보
  + Authenticated: 인증 여부

## SecurityContext
* SecurityContext: Authentication 객체가 저장되는 보관소로 필요 시 언제든지 Authentication 객체를 꺼내어 쓸 수 있도록 제공되는 클래스
  + 인증이 완료되면 HttpSession에 저장되어 어플리케이션 전반에 걸쳐 전역적인 참조 가능

* SecurityContextHolder: SecurityContext 객체 저장 방식
  + MODE_THREALOCAL: 스레드당 객체를 할당
  + MODE_INHERITABLETHREADLOCAL: 메인 스레드와 자식 스레드에 관하여 동일한 SecurityContext를 유지
  + MODE_GLOBAL: 응용 프로그램에 단 하나의 SecurityContext 를 저장

  
* 익명 사용자
  * 새로운 SecurityContext 객체를 생성하여 Holder 에 저장
  * AnonymousAutenticationFilter 에서 Token 객체를 SeucrityContext에 저장

* 인증 시
  * 새로운 SecurityContext 객체를 생성하여 SecurityContextHolder 에 저장
  * 인증 성공 후 SeucrityContext 에 UsernamePasswordAuthentication 객체를 SecurityContext에 저장

* 인증 후
  * Session 에서 SecurityContext 꺼내어 SecurityContextHolder 에서 저장
  * SecurityContext 안에 Authentication 객체가 존재하면 계속 인증을 유지한다.

* 인증 흐름
  <img src='https://user-images.githubusercontent.com/76925402/212604830-29439607-2003-4b4a-af62-1d0e413c0f5e.png'> 

