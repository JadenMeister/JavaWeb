package com.example.javapractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/logout").permitAll() //엑세스
                .anyRequest().authenticated() // 모든 요청은 인증이 필요
                .and()
            .formLogin()
                .loginPage("/login") // 로그인 페이지로 이동
                .permitAll() // 로그인 페이지는 모든 사용자에게 허용
                .and()
            .logout()
                .logoutUrl("/logout") // 로그아웃 URL 설정
                .permitAll(); // 로그아웃은 모든 사용자에게 허용

        return http.build();
    }
}