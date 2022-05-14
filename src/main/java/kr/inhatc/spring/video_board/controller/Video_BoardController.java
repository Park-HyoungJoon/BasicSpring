package kr.inhatc.spring.video_board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inhatc.spring.user.dto.BasicDto;
import kr.inhatc.spring.user.service.BasicService;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.service.Video_BoardService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class Video_BoardController {
	 
	@Autowired
	private Video_BoardService video_BoardService;
	
//	@RequestMapping("/")
//	public String hello() {
//		return "redirect:/user/userList";
//	}
	  
	@GetMapping("/video/videoList")
	public String videoList(@RequestParam(required = false, defaultValue = "") String search, Model model, @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Video_BoardDto> list = video_BoardService.search(search, pageable);
		int totalPage = list.getTotalPages();
	
		model.addAttribute("list", list);
		model.addAttribute("totalPage", totalPage);
		return "video/videoList";
	}
	
	@GetMapping("/video/videoInsert")
	public String videoWrite() {
		return "video/videoWrite";
	}
	
	// 원래 엔티티 클래스는 요청이나 응답에 사용되면 안되는데 일단은 영상 그대로 따라하고 나중에 Request, Response 만들어보자
	@PostMapping("/video/videoInsert")
	public String videoInsert(Video_BoardDto video) {
		video_BoardService.saveVideo(video);
		return "redirect:/video/videoList";
	}
	
	@GetMapping("/video/videoDetail/{id}")
	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
	public String videoDetail(@PathVariable("id") Long id, Model model) {
		Video_BoardDto video = video_BoardService.videoDetail(id);
		model.addAttribute("video", video);
		return "video/videoDetail";
	}
	
	@GetMapping("/video/videoDelete/{id}")
	public String videoDelete(@PathVariable("id") Long id) {
		video_BoardService.videoDelete(id);
		return "redirect:/video/videoList";
	}
	 
	@GetMapping("/video/videoDetail2")
	public String videoDetail2_test() {
		return "video/videoDetail2";
	}
	
}
