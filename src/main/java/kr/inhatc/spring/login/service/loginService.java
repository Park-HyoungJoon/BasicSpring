package kr.inhatc.spring.login.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.login.repository.UserRepositoy;
import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;

@Service
public class loginService {
	private final boolean yes=true;
	private final boolean no=false;
	
	@Autowired
	private UserRepositoy userRepository;
	
	public boolean confrimEail(String email) {
		System.out.println(email + "  중복검사");
		boolean vo = userRepository.existsByEmail(email);
		System.out.println(vo);
		return vo;
	}
	
	public boolean passchange(String email, String auth) {
		System.out.println("인증번호 확인");
		Integer vo = userRepository.findEmailtoNum(email, auth);
		System.out.println(vo);
		if (vo != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean pwchange(String email , String pw) {
		System.out.println("비밀번호 변경");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("암호화전 : " + pw);
		String s_pw = encoder.encode(pw);
		System.out.println("암호화후 : " + s_pw);
		Integer vo = userRepository.pwchage(email, s_pw);
		System.out.println(vo);
		if (vo == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Transactional()
	public boolean updateRendnum(String email, String randnum) {
		try {
			User user = userRepository.findByEmail(email);
			user.setRandnum(randnum);
			return yes;
		} catch (Exception e) {
			System.out.println("메일 이상함");
		}
		return no;
	}
}
