package com.example.UserServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	// CSRF 설정
        	.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/api/users/**").permitAll()  // '/api/users/'으로 시작하는 경로는 인증 없이 접근 허용
                .anyRequest().authenticated()  // 그 외의 모든 요청은 인증 필요
            )
            .httpBasic(httpBasic -> {}) // HTTP Basic 인증 활성화
            .formLogin(formLogin -> {});  // 폼 로그인 활성화

        return http.build();
    }
    
    // PasswordEncoder Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}