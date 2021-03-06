package com.example.Ruokavalio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailService userDetailsService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		 http
	        .authorizeRequests().antMatchers("/css/**").permitAll()
	        .and()
	        .authorizeRequests().antMatchers("/rekisterointi", "/tallennakayttaja").permitAll()
	        .and()
	        .authorizeRequests().anyRequest().authenticated()
	        .and()
      .formLogin()
      .loginPage("/login")
          .defaultSuccessUrl("/etusivu", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
