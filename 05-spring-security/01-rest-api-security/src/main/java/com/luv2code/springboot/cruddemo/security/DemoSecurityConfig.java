package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // User Creation using JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // the following two lines of code because we have custom tables with custom columns name
        // if we have tables and columns name as Spring Security needs we don't need them

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?"); // the ? will be filled from the login form

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    /*
    // Manual User Creation
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        // since we defined our users here, Spring Boot will not use the user/pass from the app.properties file

        // noop -> no-op means saves the password as plain old text
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();

        UserDetails mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();

        UserDetails susan = User.builder().username("susan").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                                           .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                                           .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER").requestMatchers(HttpMethod.PUT, "/api/employees")
                                                           .hasRole("MANAGER").requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        // use HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF, in general not required for stateless REST APIs [POST/PUT/PATCH/DELETE]
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
