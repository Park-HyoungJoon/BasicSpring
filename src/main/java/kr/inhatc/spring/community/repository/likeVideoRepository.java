package kr.inhatc.spring.community.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.inhatc.spring.community.entity.likeVideo;
import kr.inhatc.spring.myPage.entity.UserVideo;

public interface likeVideoRepository extends JpaRepository<likeVideo, Integer>{
	@Query(value="Select u.UId from likeVideo u where UId=?1",nativeQuery = true)
	int findUser(int id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO likeVideo (UId,UVId,like,subscribe) values (?1,?2,1,0)", nativeQuery = true)
	int addlikeVideo(int UId,int UVId);

}
