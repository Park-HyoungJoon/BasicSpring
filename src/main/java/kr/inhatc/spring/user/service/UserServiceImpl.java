package kr.inhatc.spring.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.repository.UserRepository;

@Service
//@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * 게시글 조회
	 */
	@Override
	public List<UserDto> userList() {
		List<User> list = userRepository.findAllByOrderByIdDesc();
		return list.stream().map(UserDto::new).collect(Collectors.toList());
	}
	
	/**
	 * 게시글 저장
	 */
	@Override
	public void saveUser(UserDto user) {
		userRepository.save(user.toEntity());
	}
	
	/**
	 * 게시글 상세조회
	 */
	@Override
	@Transactional
	public UserDto userDetail(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			user.increaseHits();
			return new UserDto(user);
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
