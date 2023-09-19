package com.example.rabbit;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // enable Spring Security
public class SecurityConfig {

	// function for permitting all requests. is mandatory because security
	// dependency is included
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/login", "/oauth/authorize").permitAll()
				.anyRequest().authenticated());
	}
}

