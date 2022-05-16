package kr.inhatc.spring.login.service.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.inhatc.spring.user.entity.User;
import lombok.Data;

//시큐리티가 /login 주소요청이 오면 낚아쳐서 로그인을 진행
@Data
public class PrincipalDatails implements UserDetails{
		
	private User user;				
	
	public PrincipalDatails(User user) {
		this.user = user;
	}
	
	//해당 user의 권한 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}
	
	@Override
	public String getPassword() {
		return user.getPW();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() { //계정만료?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //비밀번호 기간?
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //계정 활성화
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
