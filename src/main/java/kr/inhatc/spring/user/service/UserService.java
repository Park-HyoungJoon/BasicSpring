package kr.inhatc.spring.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.repository.UserRepository;

@Service
public class UserService {
	/*	
	*//**
		 * 게시글 조회
		 */
	
	@Autowired
	private UserRepository UserRepository;

	public List<UserDto> userList() {
		List<User> list = UserRepository.findAllByOrderByIdDesc();
		return list.stream().map(UserDto::new).collect(Collectors.toList());
	}

	/**
	 * 게시글 저장
	 */

	public void saveUser(UserDto user) {
		UserRepository.save(user.toEntity());
	}

	/**
	 * 게시글 상세조회
	 */

	@Transactional
	public UserDto userDetail(Long id) {
		Optional<User> optional = UserRepository.findById(id);
		if (optional.isPresent()) {
			User user = optional.get();
			return new UserDto(user);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * 게시글 삭제
	 */
	public void userDelete(Long id) {
		UserRepository.deleteById(id);
	}

}
