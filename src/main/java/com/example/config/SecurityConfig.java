package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Suxrob Sattorov, Tue 10:22 AM. 10/11/2022
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("suxrob").password("{noop}suxrob77").roles("ADMIN")
                .and()
                .withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/word/**").permitAll()
                .antMatchers("/login", "/auth").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
