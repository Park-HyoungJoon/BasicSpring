package kr.inhatc.spring.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.myPage.repository.UserFriendRepository;
import kr.inhatc.spring.myPage.repository.UserLectureRepository;
import kr.inhatc.spring.myPage.repository.UserVideoRepository;
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
	
	@Autowired
	private UserVideoRepository uvRepository;
	
	@Autowired
	private UserLectureRepository ulRepository;
	
	@Autowired
	private UserFriendRepository ufRepository;

	public List<UserDto> userList() {
		List<User> list = UserRepository.findAllByOrderByIdDesc();
		return list.stream().map(UserDto::new).collect(Collectors.toList());
	}
	public List<UserDto> findFriend(int id) {
		List<User> list = UserRepository.findFriend(id);
		return list.stream().map(UserDto::new).collect(Collectors.toList());
	}


	public List<UserDto> findHaveFriend(int id) {
		List<User> list = UserRepository.findHaveFriend(id);
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
	public UserDto findByUserId(int userId) {
		List<User> user = UserRepository.findUser(userId); // 유저아이디로 유저찾음
		
		List<UserDto> udt = user.stream().map(UserDto::new).collect(Collectors.toList());
		
		return udt.get(0);
		
	}

	/**
	 * 게시글 삭제
	 */
	public void userDelete(Long id) {
		UserRepository.deleteById(id);
		ulRepository.deleteUserLecture(id);
		ufRepository.deleteUserFriend(id);
		uvRepository.deleteUserVideo(id);
	}
	
	public List<UserDto> findMe(int id){
		List<User> user = UserRepository.findUser(id);
		System.out.println(user.get(0).getNick());
		return user.stream().map(UserDto::new).collect(Collectors.toList());
		
	}
	public int findUserId(String email){
		int user = 0;
		try {
		user = UserRepository.findEmailtoUser(email);
		}catch (Exception e) {
			user=0;
		}
		return user;
		
	}
	public List<UserDto> findAllData(String path){
		List<User> user = UserRepository.findAllData(path);
		return user.stream().map(UserDto::new).collect(Collectors.toList());
		
	}
	public void img_update(int userId, String profile_photo) {
		List<User> user = UserRepository.findUser(userId); // 유저아이디로 유저찾음
		System.out.println(user.get(0).getNick()+"//////////////////////////////////////////////////");
		user.get(0).setProfile_Photo(profile_photo);
		UserDto udt = new UserDto(user.get(0));
		
		saveUser(udt);
	}

	public void updateProfile(String nick, String self, long id) {
		int up = UserRepository.setProfile(nick,self,id);
	}
	public void deleteUser(long id) {
		UserRepository.deleteById(id);
		ulRepository.deleteUserLecture(id);
		ufRepository.deleteUserFriend(id);
		uvRepository.deleteUserVideo(id);
	}
}
