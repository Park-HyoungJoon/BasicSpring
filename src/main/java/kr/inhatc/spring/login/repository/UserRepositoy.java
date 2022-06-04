package kr.inhatc.spring.login.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.inhatc.spring.user.entity.User;

public interface UserRepositoy extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	public boolean existsByEmail(String email);
	
	@Query(value="SELECT * FROM `User` u WHERE email = ?1 AND Randnum = ?2", nativeQuery = true)
	Integer findEmailtoNum( String email, String Randnum);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE `User` u SET PW = ?2 WHERE email = ?1", nativeQuery = true)
	Integer pwchage(String email, String pw);
//	public List<User> findByEmailAndRandnum(String email, String Randnum);
}
		