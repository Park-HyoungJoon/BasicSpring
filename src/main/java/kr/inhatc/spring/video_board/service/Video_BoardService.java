package kr.inhatc.spring.video_board.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import kr.inhatc.spring.video_board.util.PageResultDto;

public interface Video_BoardService {


	void saveVideo(Video_BoardDto video, MultipartFile file) throws Exception;

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
				.type(dto.getType())
				.url1(dto.getUrl1())
				.url2(dto.getUrl2())
				.url3(dto.getUrl3())
				.url4(dto.getUrl4())
				.url5(dto.getUrl5())
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
				.type(entity.getType())
				.filename(entity.getFilename())
				.filepath(entity.getFilepath())
				.url1(entity.getUrl1())
				.url2(entity.getUrl2())
				.url3(entity.getUrl3())
				.url4(entity.getUrl4())
				.url5(entity.getUrl5())
				.build();
		
		return dto;
	}

	PageResultDto<Video_BoardDto, Video_Board> getList2(PageRequestDto requestDto, String type);
	
	

	List<Video_BoardDto> searchVideo(int id);

	Long updateVideo(Video_BoardDto video, MultipartFile file) throws Exception;
}
