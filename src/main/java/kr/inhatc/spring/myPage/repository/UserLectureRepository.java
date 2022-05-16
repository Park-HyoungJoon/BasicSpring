package kr.inhatc.spring.myPage.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.myPage.entity.UserLecture;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Integer>{
	@Query(value="Select v.* from UserLecture v where UId=?1",nativeQuery = true)
	public List<UserLecture> findallLectures(int id);
	
	@Query(value="select v.* from UserLecture v where v.UId=?1 and v.ULUpload = (select MAX(ULUpload) from UserLecture where UId=?1)",nativeQuery = true)
	public List<UserLecture> latestLecture(Long long1);

}
