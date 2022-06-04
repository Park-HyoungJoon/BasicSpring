package kr.inhatc.spring.login.controller;

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

import kr.inhatc.spring.login.repository.UserRepositoy;
import kr.inhatc.spring.user.entity.User;

@Controller
public class LoginController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	test
	@Autowired
	private UserRepositoy userRepositoy;
	
	
//	@GetMapping("/username")
//	@ResponseBody 
//	public String currentUserName(Principal principal) { 
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
//		UserDetails userDetails = (UserDetails)principal; String username = principal.getUsername(); 
//		String password = principal.getPassword();
//		return principal.getName(); 
//	}
	
	@GetMapping("/username")
	@ResponseBody 
	public String currentUserName() { 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String username = ((UserDetails) principal).getUsername(); 
//		String password = principal.getPassword();
		return username; 
	}


//	@GetMapping({ "", "/" })
//	public String index() {
//		return "index";
//	}
	
	@PostMapping("/success")
	public String success() {
		return "redirect:/";
	}

	@GetMapping("/userr")
	public @ResponseBody String user() {
		return "user";
	}

	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}

	@PostMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/tt")
	public String test() {
		return "login/test";
	}

	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawpass = user.getPW();
		String encpass = bCryptPasswordEncoder.encode(rawpass);
		user.setPW(encpass);
		try {
		userRepositoy.save(user);
		} catch (Exception sqlException) {
			System.out.println("실패");
		}
		
		return "redirect:/";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "login/loginForm";
	}

	@GetMapping("/joinFrom")
	public String joinForm() {
		return "login/joinForm";
	}
	
//	@Re
}
