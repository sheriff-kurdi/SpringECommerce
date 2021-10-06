package com.kurdi.springecommerce.config;

import com.kurdi.springecommerce.security.CustomAuthenticationProvider;
import com.kurdi.springecommerce.security.Roles;
import com.kurdi.springecommerce.security.filters.CustomUserNameAuthenticationFilter;
import com.kurdi.springecommerce.security.Permissions;
import com.kurdi.springecommerce.security.filters.JwtTokenVerifierFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider authenticationProvider;
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.formLogin();
*/
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new CustomUserNameAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifierFilter(),CustomUserNameAuthenticationFilter.class)
                .authorizeRequests()
                .mvcMatchers("/user")
                .permitAll()
                .mvcMatchers("/admin")
                .hasAnyAuthority(Roles.ADMIN.getRole());


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}
