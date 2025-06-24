package com.example.javapractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/hello", "/api/hello/json", "/css/**", "/js/**").permitAll()  // API는 인증 없이
                .requestMatchers("/login").permitAll()  // 로그인 페이지는 접근 허용
                .anyRequest().authenticated()  // 나머지는 인증 필요
            )
            .formLogin(form -> form
                .loginPage("/login")  // 커스텀 로그인 페이지
                .defaultSuccessUrl("/dashboard", true)  // 로그인 성공 시 이동할 페이지
                .failureUrl("/login?error=true")  // 로그인 실패 시
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // 로그아웃 URL
                .logoutSuccessUrl("/login?logout=true")  // 로그아웃 성공 시 이동할 페이지
                .invalidateHttpSession(true)  // 세션 무효화
                .deleteCookies("JSESSIONID")  // 쿠키 삭제
                .permitAll()
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("=== UserDetailsService Bean 생성됨 ===");

        // 테스트용 사용자 생성
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123456"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user) {
            @Override
            public UserDetails loadUserByUsername(String username) {
                System.out.println("로그인 시도: " + username);
                return super.loadUserByUsername(username);
            }
        };
    }
    }

