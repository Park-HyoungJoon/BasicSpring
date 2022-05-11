package kr.inhatc.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.login.entity.Member;

public interface UserRepositoy extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);
	public Member findByEmail(String email);
}
