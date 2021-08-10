package com.Defis.domain.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new NyundooUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/users/**").hasAnyAuthority("Admin")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Payments")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Medicals")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Agents")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Medicals")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Applicants")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Trainings")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Tickets")
		.antMatchers("/trainings/edit/**").hasAnyAuthority("Admin","Trainings")
		.antMatchers("/trainings/delete/**").hasAnyAuthority("Admin","Trainings")
		.antMatchers("/tickets/edit/**").hasAnyAuthority("Admin","Tickets")
		.antMatchers("/tickets/delete/**").hasAnyAuthority("Admin","Tickets")
		.antMatchers("/agents/edit/**").hasAnyAuthority("Admin","Agents")
		.antMatchers("/agents/delete/**").hasAnyAuthority("Admin","Agents")
		.antMatchers("/applicants/edit/**").hasAnyAuthority("Admin","Applicants")
		.antMatchers("/applicants/delete/**").hasAnyAuthority("Admin","Applicants")
		.antMatchers("/jobs/edit/**").hasAnyAuthority("Admin","Jobs")
		.antMatchers("/jobs/delete/**").hasAnyAuthority("Admin","Jobs")
		.antMatchers("/medicals/edit/**").hasAnyAuthority("Admin","Medicals")
		.antMatchers("/medicals/delete/**").hasAnyAuthority("Admin","Medicals")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.permitAll()
			.defaultSuccessUrl("/", true)
		.and().logout().permitAll()
			.and().rememberMe()
				.key("AbcDefgHiJklmOprsT_1234567890? j17-1372-2014")
				.tokenValiditySeconds(7 * 24 * 60 * 60);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
        .ignoring()
        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**", "/fontawesome/**", "/webfonts/**");
	}

}
