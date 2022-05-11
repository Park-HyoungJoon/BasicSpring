package kr.inhatc.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encoderPwd() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().ignoringAntMatchers("/login/**");
		http.csrf().disable();
		http.authorizeHttpRequests()
//			.antMatchers("/user/**").authenticated()
			.antMatchers("/manager/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/user/userList")
//			.usernameParameter("email")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/user/userList");
	}
	
	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
}
