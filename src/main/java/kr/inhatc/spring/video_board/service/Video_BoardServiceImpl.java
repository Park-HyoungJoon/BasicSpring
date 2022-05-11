package kr.inhatc.spring.video_board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.repository.Video_BoardRepository;

@Service
//@Transactional(readOnly = true)
public class Video_BoardServiceImpl implements Video_BoardService {
	
	@Autowired
	Video_BoardRepository video_BoardRepository;
	
	/**
	 * 게시글 조회
	 */
//	@Override
//	public List<Video_BoardDto> videoList() {
//		List<Video_Board> list = video_BoardRepository.findAllByOrderByIdDesc();
//		return list.stream().map(Video_BoardDto::new).collect(Collectors.toList());
//	}
	
////	@Transactional
//	@Override
//	public Page<Video_BoardDto> videoList(Pageable pageable) {
//		
////		Page<Video_Board> page = video_BoardRepository.findAll(pageable);
////		Page<Video_BoardDto> pageDto = page.map(Video_BoardDto::new);
////		return pageDto;
//		
//		return video_BoardRepository.findAll(pageable).map(Video_BoardDto::new);	// 코드 최적화(Entity -> DTO 변환)
//	}

	@Override
	public Page<Video_BoardDto> search(String keyword, Pageable pageable) {
		Page<Video_Board> list = video_BoardRepository.findByTitleContaining(keyword,pageable);
		Page<Video_BoardDto> listDto = list.map(Video_BoardDto::new);	
		return listDto;
	}
	
	
	/**
	 * 게시글 저장
	 */
	@Override
	public void saveVideo(Video_BoardDto video) {
		video_BoardRepository.save(video.toEntity());
	}
	
	/**
	 * 게시글 상세조회
	 */
	@Override
	@Transactional
	public Video_BoardDto videoDetail(Long id) {
		Optional<Video_Board> optional = video_BoardRepository.findById(id);
		if(optional.isPresent()) {
			Video_Board user = optional.get();
			user.increaseHits();
			return new Video_BoardDto(user);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public void videoDelete(Long id) {
		video_BoardRepository.deleteById(id);
	}






}
