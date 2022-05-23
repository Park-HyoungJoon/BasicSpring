package kr.inhatc.spring.myPage.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.myPage.entity.UserFriend;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Integer>{

	@Query(value="SELECT OtherUId from UserFriend where UId=?1",nativeQuery=true)
	public int[] searchOUId(int id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "DELETE from UserFriend where UId=?1",nativeQuery = true)
	void deleteUserFriend(long id);
}
