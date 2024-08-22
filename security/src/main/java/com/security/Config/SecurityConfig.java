package com.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //security 메서드 때문에 이거 만들어야 함.
    //이유 : 토큰 인증, 클라이언트-서버 상호작용으로 사용
    //      정상적인 경로로 요청이 이루어지게 하기 위한 스프링 시큐리티에서 제공하는 값
    //      disable = 토큰 사용하지 않겠다는 선언

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{


        http.csrf().disable();
        return http.build();
    }
}
