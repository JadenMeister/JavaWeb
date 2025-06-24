# Spring Boot Practice Project

Spring Boot 3.x를 사용한 웹 애플리케이션 학습 프로젝트입니다.

## 📋 프로젝트 개요

| 항목 | 내용 |
|------|------|
| **프레임워크** | Spring Boot 3.5.3 |
| **Java 버전** | Java 17 |
| **빌드 도구** | Gradle |
| **포트** | 8080 (기본값) |
| **패키지명** | com.example.javapractice |

## 🗂️ 프로젝트 구조

```
javapractice/
├── src/
│   ├── main/
│   │   ├── java/com/example/javapractice/
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── HelloController.java
│   │   │   │   └── LoginController.java
│   │   │   └── JavapracticeApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── main.css
│   │       ├── templates/
│   │       │   └── login.html
│   │       └── application.properties
│   └── test/
├── build.gradle
└── README.md
```

## 📦 주요 의존성

| 의존성 | 용도 |
|--------|------|
| `spring-boot-starter-web` | 웹 애플리케이션 개발 |
| `spring-boot-starter-security` | 보안 및 인증 |
| `spring-session-core` | 세션 관리 |
| `spring-boot-devtools` | 개발 도구 |
| `spring-boot-starter-test` | 테스트 프레임워크 |

## 🚀 실행 방법

### 1. Gradle 명령어로 실행
```bash
cd javapractice
./gradlew bootRun
```

### 2. IntelliJ IDEA에서 실행
1. `JavapracticeApplication.java` 파일 열기
2. `main` 메서드 옆의 ▶️ 버튼 클릭

### 3. JAR 파일로 실행
```bash
./gradlew build
java -jar build/libs/javapractice-0.0.1-SNAPSHOT.jar
```

## 🌐 API 엔드포인트

| HTTP 메서드 | 경로 | 설명 | 응답 타입 |
|-------------|------|------|-----------|
| GET | `/api/hello` | Hello World 메시지 | String |
| GET | `/api/hello/json` | JSON 형태 응답 | JSON |
| GET | `/login` | 로그인 페이지 | HTML |
| POST | `/login` | 로그인 처리 | Redirect |
| GET | `/logout` | 로그아웃 처리 | Redirect |

## 🔧 주요 설정

### Spring Security 설정

| 설정 항목 | 내용 |
|-----------|------|
| **인증 없이 접근 가능** | `/api/**`, `/login`, `/logout`, `/css/**`, `/js/**` |
| **인증 필요** | 나머지 모든 경로 |
| **CSRF 보호** | 비활성화 (REST API용) |
| **로그인 페이지** | `/login` |

### 올바른 SecurityConfig.java 설정

```java
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // CSRF 비활성화
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**", "/login", "/logout", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .permitAll()
            );
        
        return http.build();
    }
}
```

## 🐛 트러블슈팅

### 주요 오류와 해결방법

| 오류 | 원인 | 해결방법 |
|------|------|----------|
| `ERR_TOO_MANY_REDIRECTS` | Security 설정 오류 | CSRF 비활성화, API 경로 permitAll 추가 |
| `BUILD FAILED` | Java 문법 오류 | `string` → `String` 수정 |
| `401 Unauthorized` | API 경로 인증 필요 | SecurityConfig에서 `/api/**` permitAll |
| `Template not found` | 템플릿 파일 누락 | `logout.html` 생성 또는 Controller 수정 |

### 일반적인 문제 해결

| 문제 | 확인사항 |
|------|----------|
| **서버 시작 안됨** | 포트 8080 사용 중인지 확인 |
| **API 응답 없음** | SecurityConfig 설정 확인 |
| **무한 리디렉션** | CSRF 설정 및 permitAll 경로 확인 |
| **빌드 실패** | Java 문법 및 import 문 확인 |

## 🎯 학습 목표

1. ✅ **Spring Boot 기본 템플릿** 생성 및 설정
2. ✅ **@RestController** 를 사용한 Hello World API 구현
3. ⬜ **DTO + Service + Repository** 계층 구조 연습
4. ⬜ **Postman을 활용한 API 테스트**

## 📚 참고사항

### Spring Boot vs Express.js 비교

| 개념 | Spring Boot | Express.js |
|------|-------------|------------|
| **라우팅** | `@GetMapping("/path")` | `app.get('/path', ...)` |
| **컨트롤러** | `@RestController` | Router functions |
| **포트 설정** | `application.properties` | `app.listen(port)` |
| **정적 파일** | `src/main/resources/static/` | `express.static()` |
| **템플릿** | `src/main/resources/templates/` | Views directory |

### 다음 단계

- [ ] User 엔티티 및 DTO 생성
- [ ] UserService 계층 구현
- [ ] UserRepository (메모리 기반) 구현
- [ ] CRUD API 엔드포인트 추가
- [ ] Postman 컬렉션 생성 및 테스트

## 🔗 유용한 링크

- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Security 공식 문서](https://spring.io/projects/spring-security)
- [Gradle 사용법](https://gradle.org/guides/)
