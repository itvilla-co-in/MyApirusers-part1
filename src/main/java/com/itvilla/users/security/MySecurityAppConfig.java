package com.itvilla.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MySecurityAppConfig extends WebSecurityConfigurerAdapter{

	private Environment environment;
	
	@Autowired
	//public WebSecurity(Environment environment, UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder)
	public MySecurityAppConfig(Environment environment)
	{
		this.environment = environment;
		//this.usersService = usersService;
		//this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"));
		http.headers().frameOptions().disable();
		
//		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
//		.and()
//		.addFilter(getAuthenticationFilter());
//		http.headers().frameOptions().disable();
	}
	
	
	
	
}
