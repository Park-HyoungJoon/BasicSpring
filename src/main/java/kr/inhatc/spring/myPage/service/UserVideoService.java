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
import kr.inhatc.spring.myPage.repository.UserFriendRepository;
import kr.inhatc.spring.myPage.repository.UserLectureRepository;
import kr.inhatc.spring.myPage.repository.UserVideoRepository;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;

@Service
public class UserVideoService {
		
		@Autowired
		UserVideoRepository uvRepository;
		
		@Autowired
		UserLectureRepository uLRepository;

		public void saveVideo(String title,String contents,int id) {
			uvRepository.addUserVideo(title, contents, id);
		}
		
		/**
		 * 게시글 조회
		 */
		public List<UserVideoDTO> videoList(int id) {
			List<UserVideo> list = uvRepository.findallVideo(id);
			return list.stream().map(UserVideoDTO::new).collect(Collectors.toList());
		}
		public List<UserLectureDTO> uLectureList(int id) {
			List<UserLecture> list = uLRepository.findallLectures(id);	

			return list.stream().map(UserLectureDTO::new).collect(Collectors.toList());
		}
		public List<UserLectureDTO> latestuLectureList(Long long1) {
			List<UserLecture> list = uLRepository.latestLecture(long1);	
			return list.stream().map(UserLectureDTO::new).collect(Collectors.toList());
		}
		public List<UserVideoDTO> latestuVideoList(Long long1) {
			List<UserVideo> list = uvRepository.latestVideo(long1);	
			return list.stream().map(UserVideoDTO::new).collect(Collectors.toList());
		}

		public void deleteUV(int uId, Long uVId) {
			uvRepository.deleteUserVideo(uId, uVId);
		}
		@Transactional
		public UserVideoDTO uvDetail(int id) {
			Optional<UserVideo> optional = uvRepository.findById(id);
			if(optional.isPresent()) {
				UserVideo user_video = optional.get();
				user_video.increaseHits();
				return new UserVideoDTO(user_video);
			} else {
				throw new NullPointerException();
			}
		}

		@Transactional
		public void addLecture(long id, int uId,List<Video_BoardDto> searchLecture) {
			uLRepository.addLectrue(id,uId,searchLecture.get(0).getTitle(),searchLecture.get(0).getContents(),searchLecture.get(0).getUploadDate());
			
		}

		public void deleteUL(int uId, Long uLPId) {
			uLRepository.deleteUserVideo(uId, uLPId);
			
		}

		public int checkLecture(long uId, Long id) {
			int check=0;
			try {
			check = uLRepository.selectLecture(uId,id);
			}catch (Exception e) {
				check=0;
			}
			return check;
		}
		

}
