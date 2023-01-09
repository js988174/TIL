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

