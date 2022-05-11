package kr.inhatc.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.user.entity.Member;

public interface UserRepositoy extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);
	public Member findByEmail(String email);
}
