package com.security.Config;

import com.security.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //암호의 복호화가 필요함.
    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        //security 메서드 때문에 이거 만들어야 함.
        //이유 : 토큰 인증, 클라이언트-서버 상호작용으로 사용
        //      정상적인 경로로 요청이 이루어지게 하기 위한 스프링 시큐리티에서 제공하는 값
        //      disable = 토큰 사용하지 않겠다는 선언

        http.authorizeRequests()    //얘가 인가규칙
                .mvcMatchers("/guestHome").permitAll()
                .mvcMatchers("/css/**", "/js/**","/image/**").permitAll()
                    //이렇게 해야 css, js, 이미지 등이 비회원에게도 보여지게 된다.
                .mvcMatchers("/signUp").permitAll() //signUp 주소는 인증없어도 허용
                .mvcMatchers("/adminHome").hasRole("ADMIN")
                    //Enum에서 설정한 ADMIN만 접속 가능하도록 인가
                .anyRequest().authenticated();

            //.authorizeRequests() : 인가 규칙  =
            //.anyRequest().authenticated() : 모든 요청에 대한 인증 요구 = 권한이 있는 사람만 들어올 수 있게 만듬
        http.formLogin()
                .loginPage("/signIn")  //로그인 페이지 요청 주소 = 커스텀 페이지로 처리
                .loginProcessingUrl("/login_chk")   //로그인 처리 주소
                .defaultSuccessUrl("/", true) //로그인 성공시 이동할 페이지
                .usernameParameter("id")    //login.html의 아이디 입력 태그
                .passwordParameter("pw")    //login.html의 비번 입력 태그
                .permitAll()    //로그인 성공시 모든 권한을 줄 것
                .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                //.logoutUrl("/logout")  //로그아웃시 /logout 주소로 이동되도록 요청
                        .logoutSuccessUrl("/")  //로그아웃 성공시 이동할 주소
                                 //.invalidateHttpSession(true)    //로그인 성공으로 얻은 세션 제거
                .permitAll();
//        http.csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
