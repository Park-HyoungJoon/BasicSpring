package kr.inhatc.spring.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration		// @Configuration은 설정파일로 쓸 수 있는 어노테이션
@PropertySource("classpath:/application.properties")	// resources에 application.propeties 파일에 접근하기 위한 어노테이션
public class DataBaseConfiguration {
	
	@Autowired			// @Bean과 같이 자동으로 메모리에 올려줌
	private ApplicationContext applicationContext;
	
	
	@Bean		//Bean으로 메모리에 올라가고 주입이 됨
	@ConfigurationProperties(prefix = "spring.datasource.hikari")	//spring.datasource.hikari 이것을 접두어로 사용하기위해 !
	public HikariConfig hikariConfig() {		// 히카리 설정 객체
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() throws Exception {
		//datasource 만들어질때 위에 hikariConfig() 메소드가 실행되고나서 return될때 hikari 접두어를 가지고 생성되는데 
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}


	
	/**
	 * 
	 * 1. 개요 : JPA 설정
	 * 2. 처리내용 : JPA 설정 빈 등록
	 * 
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.jpa")
	public Properties hibernateConfig() {
		return new Properties();
	}
}




















