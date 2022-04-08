package kr.inhatc.spring.user.service;

import java.util.List;

import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;

public interface UserService {

	List<UserDto> userList();

	void saveUser(UserDto user);

	UserDto userDetail(Long id);

	void userDelete(Long id);

}
