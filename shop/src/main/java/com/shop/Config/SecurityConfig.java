package com.shop.Config;

import com.shop.Service.MemberService;
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
    @Autowired
    MemberService memberService;
    //로그인에 필요한 절차라서 필요함.

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.formLogin().disable();
//        http.csrf().disable();
        http.formLogin()
                .loginPage("/member/signIn")
                .defaultSuccessUrl("/")
                .usernameParameter("userId")
                .passwordParameter("password")
                .failureUrl("/member/signIn/error")
                    //지금까지 로그인 할 때는 userId와 password 값으로 지정한 것으로 로그인 하게 하는 절차
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/");
                    //로그인 실패시 로그아웃 주소로 자동 실행하고 그 주소는 메인화면이 되게끔 할 것.

        //인가, 인증, 누구든지 접근을 할 수 있는 허용 주소를 설정하기
        http.authorizeRequests()
                .mvcMatchers("/","/member/**").permitAll()
                .mvcMatchers("/css/**", "/js/**", "/image/**").permitAll()
                //첫 페이지 주소, member아래 이어지는 주소, css와 js, 이미지 파일을 회원 관계없이 전부 허용
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();


        return http.build();
    }
}
