package kr.inhatc.spring.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.inhatc.spring.login.service.loginService;

@RestController
public class login_RestController {
	
	@Autowired
	private loginService loginService;
	
	@PostMapping("/emailconfirim") 
	public boolean emailconfirim(@RequestParam("email") String email) {
		System.out.println("여기에요--------------------------------------------------" + email);
		return loginService.confrimEail(email);
	}
}
