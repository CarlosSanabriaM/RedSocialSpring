package com.uniovi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/css/**", "/img/**", "/script/**", "/", "/signup", "/admin/login").permitAll()
			.antMatchers("/fotossubidas/**").authenticated()
			.antMatchers("/user/delete/*").hasAuthority("ROLE_ADMIN")
			.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/user/**").hasAnyAuthority("ROLE_PUBLIC", "ROLE_ADMIN")
			.antMatchers("/post/**").hasAnyAuthority("ROLE_PUBLIC")
			.anyRequest().authenticated()
				.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/user/list")
			.failureUrl("/login?error")
				.and()
		.logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
}