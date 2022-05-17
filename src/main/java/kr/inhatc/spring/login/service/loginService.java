package kr.inhatc.spring.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.login.repository.UserRepositoy;
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
}
