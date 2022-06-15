package kr.inhatc.spring.mataverse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.mataverse.entity.Metaverse;

@Repository
public interface MetaverseRepository extends JpaRepository<Metaverse, Long>{
	
//	Page<Metaverse> findAllByOrderByIdDesc(Pageable pageable);


	
	
}
