package kr.inhatc.spring.mataverse.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inhatc.spring.mataverse.dto.MetaverseDTO;

public interface MetaverseService {
	


	Page<MetaverseDTO> metaverseList(Pageable pageable);
}
