package kr.inhatc.spring.myPage.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.myPage.entity.UserLecture;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Integer>{
	@Query(value="Select v.* from UserLecture v where UId=?1",nativeQuery = true)
	public List<UserLecture> findallLectures(int id);
	
	@Query(value="select v.* from UserLecture v where v.UId=?1 and v.ULUpload = (select MAX(ULUpload) from UserLecture where UId=?1)",nativeQuery = true)
	public List<UserLecture> latestLecture(Long long1);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "DELETE from UserLecture where UId=?1",nativeQuery = true)
	void deleteUserLecture(long id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="INSERT INTO UserLecture (LPId,UId,video_title,video_contents,ULUpload) values(?1,?2,?3,?4,?5)" ,nativeQuery = true)
	public void addLectrue(long id, int uId,String vt,String vc,String up);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value= "DELETE from UserLecture where UId=?1 and ULPId=?2",nativeQuery = true)
	public void deleteUserVideo(int uId, Long uLPId);
	
	@Query(value="select v.UId from UserLecture v where v.UId=?1 and v.LPId = ?2",nativeQuery = true)
	public int selectLecture(long uId, Long id);
}
