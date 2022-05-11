package kr.inhatc.spring.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.inhatc.spring.user.entity.Member;
import kr.inhatc.spring.user.repository.UserRepositoy;

@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepositoy userRepositoy;

	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "login/success";
	}

	@GetMapping("/userr")
	public @ResponseBody String user() {
		return "user";
	}

	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}

	@GetMapping("/login")
	public @ResponseBody String login() {
		return "login";
	}

	@PostMapping("/join")
	public String join(Member user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawpass = user.getPassword();
		String encpass = bCryptPasswordEncoder.encode(rawpass);
		user.setPassword(encpass);
		try {
		userRepositoy.save(user);
		} catch (Exception sqlException) {
			System.out.println("실패");
		}
		
		return "redirect:/loginForm";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "login/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "login/joinForm";
	}
}
