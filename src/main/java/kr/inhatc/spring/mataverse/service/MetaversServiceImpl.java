package kr.inhatc.spring.mataverse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.mataverse.dto.MetaverseDTO;
import kr.inhatc.spring.mataverse.entity.Metaverse;
import kr.inhatc.spring.mataverse.repository.MetaverseRepository;

//@Service
//@Transactional(readOnly = true)
//public class MetaversServiceImpl implements MetaverseService {

//	@Autowired
//	MetaverseRepository metarverseRepository;
//	

//	@Transactional
//	@Override
////	public Page<MetaverseDTO> metaverseList(Pageable pageable) {
////		
////		Page<Metaverse> page = metarverseRepository.findAll(pageable);
////		Page<MetaverseDTO> pageDto = page.map(MetaverseDTO::new);
////		return pageDto;
//	}
//	
//
// 
//



//}
