package com.learnspring.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails mittu = User.builder()
                .username("mittu")
                .password("{noop}mittu123")
                .roles("EMPLOYEE")
                .build();

        UserDetails navya = User.builder()
                .username("navya")
                .password("{noop}navya123")
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(mittu, navya);
    }

    // Add support for JDBC to get users and pwds from DB (no more hardcoded users and pwds)
    // using default table names
   /* @Bean
    public UserDetailsManager userDetailsManagerDefalutTables(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Add support for JDBC to get users and pwds from DB (no more hardcoded users and pwds)
    // using custom table names
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw, active from members where user_id?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }*/


/*    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/remove/**").hasRole("MANAGER"));

        // use HTTP BASiC Authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross SIte Request Forgery
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }*/
}
