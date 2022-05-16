package kr.inhatc.spring.video_board.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResultDto<DTO,En> {
	private List<DTO> dtoList;
	private int totalPage;
	private int page;
	private int size;
	private int start, end;
	private boolean prev, next;
	private List<Integer> pageList;
	
	public PageResultDto(Page<En> result, Function<En,DTO> fn) {
		super();
		this.dtoList = result.stream().map(fn).collect(Collectors.toList());
		
		totalPage = result.getTotalPages();		// 전체 페이지
		
		makePageList(result.getPageable());		//페이지 리스트
	}
	
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		start = tempEnd -9;		// 시작 번호는 끝 번호에서 9를 뺀 값
		prev = start > 1;		// 시작 번호가 1보다 크면 true
		end = totalPage > tempEnd ? tempEnd : totalPage;	// 마지막 번호
		next = totalPage > tempEnd;
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());  // 페이지 리스트
	}
}
