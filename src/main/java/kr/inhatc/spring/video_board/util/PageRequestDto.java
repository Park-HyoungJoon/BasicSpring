package kr.inhatc.spring.video_board.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kr.inhatc.spring.video_board.entity.Video_Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PageRequestDto {
	
	private int page;
	private int size;
	private String type;
	private String keyword;
	
	public PageRequestDto() {
		this.page = 1;
		this.size = 8;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page -1, size,sort);
	}
	
}
