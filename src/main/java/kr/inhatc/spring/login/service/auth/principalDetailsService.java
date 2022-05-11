package kr.inhatc.spring.login.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.login.entity.Member;
import kr.inhatc.spring.login.repository.UserRepositoy;



//시큐리티 설정에서 로그인 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어 있는 loadUserByUsername 함수 실행
@Service
public class principalDetailsService implements UserDetailsService{
	@Autowired
	private UserRepositoy userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("==================> email: " + email);
		Member userEntity = userRepository.findByEmail(email);
		if(userEntity != null) {
			return new PrincipalDatails(userEntity);
		}
		return null;
	}	
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("==================> username: " + username);
//		User userEntity = userRepository.findByUsername(username);
//		if(userEntity != null) {
//			return new PrincipalDatails(userEntity);
//		}
//		return null;
//	}
}
