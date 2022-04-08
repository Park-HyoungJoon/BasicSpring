package kr.inhatc.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

// 첨부파일과 관련된 자동 구성을 사용하지 않도록 설정(즉, WebMvcConfiguraiton.java파일 설정을 쓰기위해서)
//@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MyProejctApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyProejctApplication.class, args);
	}

}
