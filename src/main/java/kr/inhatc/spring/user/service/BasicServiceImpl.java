package kr.inhatc.spring.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.user.dto.BasicDto;
import kr.inhatc.spring.user.entity.Basic;
import kr.inhatc.spring.user.repository.BasicRepository;

@Service
//@Transactional(readOnly = true)
public class BasicServiceImpl implements BasicService {
	
	@Autowired
	BasicRepository userRepository;
	
	/**
	 * 게시글 조회
	 */
	@Override
	public List<BasicDto> userList() {
		List<Basic> list = userRepository.findAllByOrderByIdDesc();
		return list.stream().map(BasicDto::new).collect(Collectors.toList());
	}
	
	/**
	 * 게시글 저장
	 */
	@Override
	public void saveUser(BasicDto user) {
		userRepository.save(user.toEntity());
	}
	
	/**
	 * 게시글 상세조회
	 */
	@Override
	@Transactional
	public BasicDto userDetail(Long id) {
		Optional<Basic> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			Basic user = optional.get();
			user.increaseHits();
			return new BasicDto(user);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public void userDelete(Long id) {
		userRepository.deleteById(id);
	}

}
