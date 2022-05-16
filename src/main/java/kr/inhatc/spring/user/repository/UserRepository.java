package kr.inhatc.spring.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findAllByOrderByIdDesc();
	
	@Query(value="Select u.* from User u where UId=?1",nativeQuery = true)
	List<User> findUser(long user);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="Update User u set u.Nick=?1,u.self=?2,u.PW=?3 where u.UId=?4", nativeQuery = true)
	int setProfile(String nick, String self, String pW, long id);
	


	
	
}
