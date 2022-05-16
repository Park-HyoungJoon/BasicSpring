package kr.inhatc.spring.video_board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inhatc.spring.user.dto.BasicDto;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import kr.inhatc.spring.video_board.util.PageResultDto;

public interface Video_BoardService {


	void saveVideo(Video_BoardDto video);

	Video_BoardDto videoDetail(Long id);

	void videoDelete(Long id);
	
	Page<Video_BoardDto> search(String keyword, Pageable pageable);
	
	PageResultDto<Video_BoardDto,Video_Board> getList(PageRequestDto requestDto);
	
	default Video_Board dtoToEntity(Video_BoardDto dto) {
		Video_Board entity = Video_Board.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.contents(dto.getContents())
				.hitCnt(dto.getHitCnt())
				.uploadDate(dto.getUploadDate())
				.creator(dto.getCreator())
				.build();
		return entity;
	}
	
	
	default Video_BoardDto entityToDto(Video_Board entity) {
		
		Video_BoardDto dto = Video_BoardDto.builder()
				.id(entity.getId())
				.title(entity.getTitle())
				.contents(entity.getContents())
				.hitCnt(entity.getHitCnt())
				.uploadDate(entity.getUploadDate())
				.creator(entity.getCreator())
				.build();
		
		return dto;
	}
}
