package kr.inhatc.spring.video_board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import kr.inhatc.spring.video_board.entity.Video_Board;

@Repository
public interface Video_BoardRepository extends JpaRepository<Video_Board, Long>, QuerydslPredicateExecutor<Video_Board>{
	
	//Page<Video_Board> findAllByOrderByIdDesc(Pageable pageable);
	Page<Video_Board> findByTitleContaining(String keyword, Pageable pageable);

	@Query(value="SELECT v.* from Video_Board v where video_type = '개발 · 프로그래밍'", nativeQuery = true)
	Page<Video_Board> find(BooleanBuilder booleanBuilder, Pageable pageable);



	
	
}
