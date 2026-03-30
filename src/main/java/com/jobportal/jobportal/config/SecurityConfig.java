package com.jobportal.jobportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 🔥 allow all
            );

        return http.build();
    }

	public JwtFilter getJwtFilter() {
		return jwtFilter;
	}

	public void setJwtFilter(JwtFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}
}