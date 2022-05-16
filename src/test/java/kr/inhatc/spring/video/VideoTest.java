package kr.inhatc.spring.video;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.service.Video_BoardService;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import kr.inhatc.spring.video_board.util.PageResultDto;

@SpringBootTest
public class VideoTest {

	@Autowired
	Video_BoardService video_BoardService;

	@Test
	public void testSearch() {
		
		PageRequestDto pageRequestDTO = PageRequestDto.builder().page(1).size(7).type("t").keyword("자바11").build();
		
		PageResultDto<Video_BoardDto, Video_Board> resultDTO = video_BoardService.getList(pageRequestDTO);


		System.out.println("PREV: " + resultDTO.isPrev());
		System.out.println("NEXT: " + resultDTO.isNext());
		System.out.println("TOTAL: " + resultDTO.getTotalPage());

		System.out.println("-------------------------------------");

		for (Video_BoardDto video_BoardDto : resultDTO.getDtoList()) {
			System.out.println(video_BoardDto);
		}

		// 페이지 번호 정보
		System.out.println("-------------------------------------");
		resultDTO.getPageList().forEach(i -> System.out.println(i));

	}

}
