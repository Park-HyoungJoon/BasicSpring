package kr.inhatc.spring.matabus.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.inhatc.spring.login.entity.Member;
import kr.inhatc.spring.login.repository.UserRepositoy;

@Controller
public class MetabusController{

	
//	메타버스 메인 창
	@RequestMapping("/metabus")
	public String metabusMain() {
		return "metabus/metabusMain";
	}
	

	
}
