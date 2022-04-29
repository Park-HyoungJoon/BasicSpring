package kr.inhatc.spring.user_video.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.user_video.dto.User_VideoDTO;
import kr.inhatc.spring.user_video.entity.user;
import kr.inhatc.spring.user_video.entity.User_Video;
import kr.inhatc.spring.user_video.entity.Video;
import kr.inhatc.spring.user_video.repository.User_VideoRepository;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class User_VideoService {
	private static EntityManager em;
	@Autowired
	private User_VideoRepository repository;
	@Transactional
	public List<User_VideoDTO> userList(int id){
		List<User_Video> uvEntity = repository.findByUser(id);
		log.info(uvEntity.get(0).getUser().getMm());
		log.info(uvEntity.get(0).getPlayTime());
		return uvEntity.stream().map(User_VideoDTO::new).collect(Collectors.toList());
		
	}
}
