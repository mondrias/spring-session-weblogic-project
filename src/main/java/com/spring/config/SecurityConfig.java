package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getCustomAuthenticationProvider());
	
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll().antMatchers("/admin**").access("hasRole('ROLE_ADMIN')").antMatchers("/dba/**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')").and().formLogin().loginPage("/").permitAll().defaultSuccessUrl("/admin").and().logout().logoutSuccessUrl("/");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	public CustomAuthenticationProvider getCustomAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

}