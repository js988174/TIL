package com.spring.site.etc;

import com.spring.site.service.MemberService;
import com.spring.site.web.filter.LoginCheckFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    private Token token;

    private UserDetailsService userDetailsService;

    private LoginAuthProvider loginAuthProvider;

    @Autowired
    private LoginCheckFilter loginCheckFilter;


    /* static 관련설정은 무시 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web .ignoring().antMatchers(
            "/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests() //
                .antMatchers("/login","/loginForm","/","member/add", "member/list").permitAll() // 누구나 접근 허용
                .antMatchers("/member/").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 7
                .loginPage("/loginForm") // 로그인 페이지 링크
                .usernameParameter("id")
                .passwordParameter("pw")
                .failureHandler(loginCheckFilter)
                .loginProcessingUrl("/login")//로그인 동작
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout() // 8
                .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
                .permitAll();
//                .and()
//                .addFilterBefore(new TokenFilter(token),
//                        UsernamePasswordAuthenticationFilter.class);

        System.out.println("세큐리티 컨피규어 로그");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new LoginAuthProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}