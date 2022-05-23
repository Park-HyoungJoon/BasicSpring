package kr.inhatc.spring.myPage.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kr.inhatc.spring.myPage.entity.UserVideo;
import kr.inhatc.spring.video_board.entity.Video_Board;


@Repository
public interface UserVideoRepository extends JpaRepository<UserVideo, Integer>, QuerydslPredicateExecutor<UserVideo>{
	@Query(value="Select v.* from UserVideo v where UId=?1",nativeQuery = true)
	public List<UserVideo> findallVideo(int id);

	@Query(value="select v.* from UserVideo v where v.UId=?1 and v.UVUpload = (select MAX(UVUpload) from UserVideo where UId=?1)",nativeQuery = true)
	public List<UserVideo> latestVideo(Long long1);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO UserVideo (UVTitle,UVContents,UId,UVHitCnt,UVUpload) values (?1,?2,?3,0,now())", nativeQuery = true)
	int addUserVideo(String title, String contents, int id);

	Page<UserVideo> findByUVTitleContaining(String keyword, Pageable pageable);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "DELETE from UserVideo where UId=?1",nativeQuery = true)
	void deleteUserVideo(long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "DELETE from UserVideo where UId=?1 and UVId=?2",nativeQuery = true)
	void deleteUserVideo(int uId, Long uVId);


}
