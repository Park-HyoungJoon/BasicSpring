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
	@Query(value="Update User u set u.Nick=?1,u.self=?2 where u.UId=?3", nativeQuery = true)
	int setProfile(String nick, String self, long id);
	
	@Query(value="SELECT u.* from User u where email=?1", nativeQuery = true)
	List<User> findAllData(String email);

	@Query(value="SELECT u.UId from User u where email=?1", nativeQuery = true)
	int findEmailtoUser(String email);
	
	@Query(value="SELECT json_arrayagg(json_object('id',UId,'name',Nick,'price', self)) FROM User where UId=?1", nativeQuery=true)
	String jsonUser(int i);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "DELETE from User where UId=?1",nativeQuery = true)
	void deleteUser(long id);
	
}
