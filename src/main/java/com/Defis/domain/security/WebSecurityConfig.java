package com.Defis.domain.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.spel.spi.EvaluationContextExtension;
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
	
	@Bean
    EvaluationContextExtension securityExtension() {
        return new SecurityEvaluationContextExtension();
    }
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/users/**").hasAnyAuthority("Admin")
		.antMatchers("/schedules/**").hasAnyAuthority("Admin")
		.antMatchers("/payments/**").hasAnyAuthority("Admin")
		.antMatchers("/medicals/**").hasAnyAuthority("Admin","Medicals")
		.antMatchers("/agents/**").hasAnyAuthority("Admin","Agents")
		.antMatchers("/applicants/**").hasAnyAuthority("Admin","Applicants")
		.antMatchers("/trainings/**").hasAnyAuthority("Admin","Trainings")
		.antMatchers("/tickets/**").hasAnyAuthority("Admin","Tickets")
		.antMatchers("/payments/**").hasAnyAuthority("Admin","Births")
		.antMatchers("/births/**").hasAnyAuthority("Admin","Tasks")
		.antMatchers("/passports/**").hasAnyAuthority("Admin","Passports")
		.antMatchers("/visas/**").hasAnyAuthority("Admin","Visas")
		.antMatchers("/jobs/**").hasAnyAuthority("Admin","Jobs")
		.antMatchers("/Marketers/**").hasAnyAuthority("Admin","Marketers")
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
		.antMatchers("/births/edit/**").hasAnyAuthority("Admin","Births")
		.antMatchers("/births/delete/**").hasAnyAuthority("Admin","Births")
		.antMatchers("/schedules/edit/**").hasAnyAuthority("Admin","Tasks")
		.antMatchers("/schedules/delete/**").hasAnyAuthority("Admin","Tasks")
		.antMatchers("/visas/edit/**").hasAnyAuthority("Admin","Visas")
		.antMatchers("/visas/delete/**").hasAnyAuthority("Admin","Visas")
		.antMatchers("/payments/edit/**").hasAnyAuthority("Admin","Payments")
		.antMatchers("/payments/delete/**").hasAnyAuthority("Admin","Payments")
		.antMatchers("/passports/edit/**").hasAnyAuthority("Admin","Passports")
		.antMatchers("/passports/delete/**").hasAnyAuthority("Admin","Passports")
		.antMatchers("/Marketers/edit/**").hasAnyAuthority("Admin","Marketers")
		.antMatchers("/Marketers/delete/**").hasAnyAuthority("Admin","Marketers")
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
