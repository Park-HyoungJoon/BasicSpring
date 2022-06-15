package kr.inhatc.spring.mataverse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.mataverse.entity.Metaverse;
import kr.inhatc.spring.video_board.entity.Video_Board;

@Repository
public interface MetaverseRepository extends JpaRepository<Metaverse, Long>, QuerydslPredicateExecutor<Metaverse>{
	
	Page<Metaverse> findAllByOrderByIdDesc(Pageable pageable);


	
	
}
