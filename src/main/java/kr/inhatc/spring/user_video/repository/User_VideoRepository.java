package kr.inhatc.spring.user_video.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.user_video.dto.User_VideoDTO;
import kr.inhatc.spring.user_video.entity.user;
import kr.inhatc.spring.user_video.entity.User_Video;

@Repository
public interface User_VideoRepository extends JpaRepository<User_Video, Integer> {

		@Query(value="Select u.* from User_Video u where u.id=?1",nativeQuery = true)
		public List<User_Video> findByUser(int id);
	
}
