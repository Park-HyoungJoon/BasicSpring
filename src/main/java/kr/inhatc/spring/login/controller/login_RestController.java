package kr.inhatc.spring.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.inhatc.spring.login.service.SendMail;
import kr.inhatc.spring.login.service.UtilService;
import kr.inhatc.spring.login.service.loginService;
import kr.inhatc.spring.login.service.auth.PrincipalDatails;
import kr.inhatc.spring.login.service.auth.principalDetailsService;

@RestController
public class login_RestController {
	
	@Autowired
	private loginService loginService;
	
	@Autowired
	private UtilService utilService;
	
	@Autowired
	private SendMail sendMail;
	
	@PostMapping("/emailconfirim") 
	public boolean emailconfirim(@RequestParam("email") String email) {
		System.out.println("여기에요--------------------------------------------------" + email);
		return loginService.confrimEail(email);
	}
	
	@PostMapping("/sendMail")
	public boolean sendEmail(@AuthenticationPrincipal PrincipalDatails datails) {
		String email = datails.getUsername();
		String randnum = utilService.GetRandomNum(10);
		if(loginService.updateRendnum(email, randnum)) {
			return sendMail.sendmail(email, "안녕하세요 E-do입니다.", "인증번호는" + randnum + "입니다.");
		}
		return false;
	}
}
