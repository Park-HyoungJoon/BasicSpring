package kr.inhatc.spring.myPage.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.myPage.repository.UserFriendRepository;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.repository.UserRepository;

@Service
public class UserFriendService {

	@Autowired
	UserFriendRepository ufRepository;

	@Autowired
	UserRepository uRepository;
	
	public String findMyFriend(int id) {
		int num = 0;
		String result = "";
		int[] ouid = ufRepository.searchOUId(id);
		String[] path = new String[ouid.length];
		for(int i : ouid) {
			path[num] = uRepository.jsonUser(i);
			num++;
		}
		for (int i = 0; i < path.length; i++) {
			result += path[i];
		}
		result = result.replace("][", ",");
		return result;
	}

	public void addFriend(int id, int UId) {
		ufRepository.addFriend(id,UId);
		
	}
}
