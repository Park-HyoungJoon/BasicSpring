package kr.inhatc.spring.myPage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.myPage.entity.UserVideo;


@Repository
public interface UserVideoRepository extends JpaRepository<UserVideo, Integer>{
	@Query(value="Select v.* from Video v",nativeQuery = true)
	public List<UserVideo> findallVideo();

}
