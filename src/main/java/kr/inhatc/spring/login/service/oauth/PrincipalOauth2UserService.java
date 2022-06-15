package kr.inhatc.spring.login.service.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.login.repository.UserRepositoy;
import kr.inhatc.spring.login.service.auth.PrincipalDatails;
import kr.inhatc.spring.user.entity.User;
import net.bytebuddy.implementation.bind.annotation.Super;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder; 
//	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepositoy repository;
	
	//sss
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration :" + userRequest.getClientRegistration());
		System.out.println("getAccessToken :" + userRequest.getAccessToken());
//		System.out.println(super.loadUser(userRequest).getAttributes());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
//		System.out.println("==========>" + oAuth2User.getAttributes());
//		
		String provider = userRequest.getClientRegistration().getClientId();
		String protiderid = oAuth2User.getAttribute("name");
		String username = provider+"_"+protiderid;
//		String password = "q1w2e3r4r4e3w2q1";//encoder.encode("gg");
		String password = encoder.encode("gg");
 		String email = oAuth2User.getAttribute("email");
 		String role = "ROLE_USER";
 		
//		
 		User userEntity = repository.findByEmail(email);
// 		
 		if(userEntity == null) {
 			userEntity = User.builder()
 					.Nick(username)
 					.PW(password)
 					.email(email)
 					.Role(role)
// 					.provider(provider)
// 					.providerid(protiderid)
 					.build();
 			repository.save(userEntity);
 					
 		} 
// 		
//		return super.loadUser(userRequest);
		return new PrincipalDatails(userEntity, oAuth2User.getAttributes());
	}
}
