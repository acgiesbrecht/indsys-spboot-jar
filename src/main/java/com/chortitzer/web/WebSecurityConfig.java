/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Have to disable it for POST methods:
        // http://stackoverflow.com/a/20608149/1199132
        http.csrf().disable();

        // Logout and redirection:
        // http://stackoverflow.com/a/24987207/1199132
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl(
                        "/login.xhtml");

        http.authorizeRequests()
                // Some filters enabling url regex:
                // http://stackoverflow.com/a/8911284/1199132
                .regexMatchers(
                        "\\A/page1.xhtml\\?param1=true\\Z",
                        "\\A/page2.xhtml.*")
                .permitAll()
                //Permit access for all to error and denied views
                .antMatchers("/500.xhtml", "/denied.xhtml")
                .permitAll()
                // Only access with admin role
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                //Permit access only for some roles
                .antMatchers("/usi/**")
                .hasAnyRole("ADMIN", "energia")
                //If user doesn't have permission, forward him to login page
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index.xhtml")
                .and().exceptionHandling().accessDeniedPage("/denied.xhtml");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        //Configure roles and passwords as in-memory authentication
        auth.inMemoryAuthentication()
                .withUser("administrator")
                .password("pass")
                .roles("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("energia")
                .password("energia")
                .roles("energia");
    }
}
