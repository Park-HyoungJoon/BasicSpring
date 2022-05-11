package kr.inhatc.spring.myPage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.myPage.entity.UserLecture;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Integer>{
	@Query(value="Select v.* from UserLecture v",nativeQuery = true)
	public List<UserLecture> findallLectures();

}
