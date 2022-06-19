package kr.inhatc.spring.community.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.community.entity.likeVideo;
import kr.inhatc.spring.myPage.entity.UserVideo;

@Repository
public interface likeVideoRepository extends JpaRepository<likeVideo, Integer>{
	@Modifying
	@Query(value="Select u.UId from likeVideo u where u.UId=?1",nativeQuery = true)
	List<Integer> findUser(int id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO likeVideo (UId,UVId,likeV,subscribe) values (?1,?2,1,0)", nativeQuery = true)
	int addlikeVideo(int UId,int UVId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="Update likeVideo u set u.likeV=?3 where u.UId=?1 and u.UVId=?2", nativeQuery = true)
	void updateHeart(int id, int id2, int heart);

}
