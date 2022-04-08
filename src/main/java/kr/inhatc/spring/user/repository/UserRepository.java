package kr.inhatc.spring.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findAllByOrderByIdDesc();



	
	
}
