package kr.inhatc.spring.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.user.entity.User;

public interface UserRepositoy extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	public boolean existsByEmail(String email);
}
