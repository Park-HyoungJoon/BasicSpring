package kr.inhatc.spring.myPage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.inhatc.spring.myPage.dto.FriendDTO;
import kr.inhatc.spring.myPage.repository.UserFriendRepository;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.repository.UserRepository;

@Service
public class UserFriendService {

	@Autowired
	UserFriendRepository ufRepository;

	@Autowired
	UserRepository uRepository;
	
	public List<FriendDTO> findMyFriend(int id) {
		int num = 0;
		String result = "";
		int[] ouid = ufRepository.searchOUId(id);
		String[] path = new String[ouid.length];
		List<User> findUser = uRepository.findUser(id);
		List<FriendDTO> getFR = new ArrayList<FriendDTO>();
		for (int i = 0; i < findUser.size(); i++) {
			getFR.add(new FriendDTO(findUser.get(i).getId(),findUser.get(i).getNick(),findUser.get(i).getProfile_Photo()));
					
		}
		return getFR;
	}

	public void addFriend(int id, int UId) {
		ufRepository.addFriend(id,UId);
		
	}
}
