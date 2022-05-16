package kr.inhatc.spring.myPage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.myPage.entity.UserLecture;
import kr.inhatc.spring.myPage.entity.UserVideo;


@Repository
public interface UserVideoRepository extends JpaRepository<UserVideo, Integer>{
	@Query(value="Select v.* from UserVideo v where UId=?1",nativeQuery = true)
	public List<UserVideo> findallVideo(int id);

	@Query(value="select v.* from UserVideo v where v.UId=?1 and v.UVUpload = (select MAX(UVUpload) from UserVideo where UId=?1)",nativeQuery = true)
	public List<UserVideo> latestVideo(Long long1);
}
