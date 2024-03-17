package com.vk.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC for custom tables
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // retrieve user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        // retrieve  the roles  by username; user_id is passed from login form
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    /*
    // add support for JDBC for predefined tables
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }
     */


    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User
                .builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User
                .builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User
                .builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // any request to the app must be authenticated
        http
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")// if role is employee, grant access to / path
                        .requestMatchers("/leaders/**").hasRole("MANAGER")// if role is managers, grant access to /leaders path
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.loginPage("/showLoginPage") // shows the custom form at this url
                                       .loginProcessingUrl("/authenticateTheUser") // login form should post the data at this url for processing, we don't need to do process it,
                                       // spring will manage this on it's own
                                       .permitAll())  // allow everyone to see the login page. No need to be logged in for that
                .logout(logout -> logout.permitAll()) // allows logout for everyone
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/custom-access-denied")) // if access is denied, due to lack of permissions go to this url
        ;

        return http.build();

    }

}
