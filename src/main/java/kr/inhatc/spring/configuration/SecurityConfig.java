package kr.inhatc.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import kr.inhatc.spring.login.service.oauth.PrincipalOauth2UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalOauth2UserService oauth2UserService;
	
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
			.antMatchers("/video/**").authenticated()
			.antMatchers("/metaverse/**").authenticated()
			.antMatchers("/community/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/")
//			.usernameParameter("email")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login()
			.loginPage("/")
			.userInfoEndpoint()
			.userService(oauth2UserService);
	}
	
	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

}
