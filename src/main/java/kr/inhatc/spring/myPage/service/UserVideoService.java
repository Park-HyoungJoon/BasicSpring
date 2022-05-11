package kr.inhatc.spring.myPage.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.myPage.dto.UserLectureDTO;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.entity.UserLecture;
import kr.inhatc.spring.myPage.entity.UserVideo;
import kr.inhatc.spring.myPage.repository.UserLectureRepository;
import kr.inhatc.spring.myPage.repository.UserVideoRepository;

@Service
public class UserVideoService {
		
		@Autowired
		UserVideoRepository uvRepository;
		
		@Autowired
		UserLectureRepository uLRepository;
		
		
		/**
		 * 게시글 조회
		 */
		public List<UserVideoDTO> videoList() {
			List<UserVideo> list = uvRepository.findAll();	
			System.out.println(list.get(0).getUVId());
			return list.stream().map(UserVideoDTO::new).collect(Collectors.toList());
		}
		public List<UserLectureDTO> uLectureList() {
			List<UserLecture> list = uLRepository.findAll();	
			System.out.println(list.get(0).getULPId());
			return list.stream().map(UserLectureDTO::new).collect(Collectors.toList());
		}

}
