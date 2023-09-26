package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // enable Spring Security
public class SecurityConfig {

	// function for permitting all requests. is mandatory because security. H2
	// console does not work without these lines
	// dependency is included
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz.anyRequest().permitAll()).csrf().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}

}