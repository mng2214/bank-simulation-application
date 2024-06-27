package com.bank.config;


import com.bank.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {


    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/account/**").hasAuthority("Admin")
                        .requestMatchers("/transaction/**").hasAnyAuthority("Admin","Cashier")
                        .requestMatchers("/login","/")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form->form
                        .loginPage("/login")
                        .successHandler(authSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout->logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"))
                .rememberMe(remember ->remember
                        .tokenValiditySeconds(300)
                        .key("bankapp")
                        .userDetailsService(securityService))
                .build();
    }
}