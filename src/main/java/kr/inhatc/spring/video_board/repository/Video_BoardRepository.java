package kr.inhatc.spring.video_board.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import com.querydsl.core.BooleanBuilder;

import kr.inhatc.spring.video_board.entity.Video_Board;

@Repository
public interface Video_BoardRepository extends JpaRepository<Video_Board, Long>, QuerydslPredicateExecutor<Video_Board>{
	
	//Page<Video_Board> findAllByOrderByIdDesc(Pageable pageable);
	Page<Video_Board> findByTitleContaining(String keyword, Pageable pageable);
	
	 
	
	@Transactional
	@Query(value = "SELECT vb.* FROM Video_Board vb WHERE vb.video_id = ?1 ",nativeQuery=true)
	List<Video_BoardDto> searchVideo(int id);



	Page<Video_Board> findAllByType(String type, Pageable pageable);

	
//	@Transactional
//	@Query("select d from Video_Board d where d.type=:type")
//	Page<Video_Board> findByType(String type,BooleanBuilder booleanBuilder, Pageable pageable);

	



	
	
}
