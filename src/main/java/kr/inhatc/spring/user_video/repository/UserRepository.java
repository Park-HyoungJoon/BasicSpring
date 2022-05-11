package kr.inhatc.spring.user_video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.user_video.entity.user;

public interface UserRepository extends JpaRepository<user, Integer>{
	
}
