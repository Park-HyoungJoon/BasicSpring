package kr.inhatc.spring.video_board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inhatc.spring.user.dto.BasicDto;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;

public interface Video_BoardService {


	void saveVideo(Video_BoardDto video);

	Video_BoardDto videoDetail(Long id);

	void videoDelete(Long id);
	
	Page<Video_BoardDto> search(String keyword, Pageable pageable);

}
