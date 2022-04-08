package kr.inhatc.spring.user.service;

import java.util.List;

import kr.inhatc.spring.user.dto.BasicDto;

public interface BasicService {

	List<BasicDto> userList();

	void saveUser(BasicDto user);

	BasicDto userDetail(Long id);

	void userDelete(Long id);

}
