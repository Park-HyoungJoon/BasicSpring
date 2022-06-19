package kr.inhatc.spring.video_board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.inhatc.spring.myPage.service.UserVideoService;
import kr.inhatc.spring.user.service.UserService;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.service.Video_BoardService;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class Video_BoardController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private Video_BoardService video_BoardService;
	
	@Autowired
	UserVideoService ulService;
	
	@RequestMapping("/")
	public String hello() {
		return "user/main";
	}
	  
//	@GetMapping("/video/videoList")
//	public String videoList(@RequestParam(required = false, defaultValue = "") String search, Model model, @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<Video_BoardDto> list = video_BoardService.search(search, pageable);
//		int totalPage = list.getTotalPages();
//	
//		model.addAttribute("list", list);
//		model.addAttribute("totalPage", totalPage);
//		return "video/videoList";
//	}
	
	
	/**
	 * 
	 * 
	 * 출력
	 */
	@GetMapping("/video/videoList")
	public String videoList(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		model.addAttribute("list", video_BoardService.getList(pageRequestDto));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/security")
	public String videoList_security(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "보안 · 네트워크";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/progamming")
	public String videoList_progamming(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "개발 · 프로그래밍";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/data")
	public String videoList_data(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "데이터 사이언스";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/creative")
	public String videoList_creative(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "크리에이티브";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/marketing")
	public String videoList_marketing(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "직무 · 마케팅";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/language")
	public String videoList_language(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "학문 · 외국어";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/career")
	public String videoList_career(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "커리어";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	@GetMapping("/video/type/refinement")
	public String videoList_refinement(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		model.addAttribute("check",UId);	
		
		String type = "교양";
		model.addAttribute("list", video_BoardService.getList2(pageRequestDto,type));
		return "video/videoList";
	}
	
	/**
	 * 
	 * 삽입
	 */
	
	@GetMapping("/video/videoInsert")
	public String videoWrite() {
		return "video/videoWrite";
	}
	
	@PostMapping("/video/videoInsert")
	public String videoInsert(Video_BoardDto video, MultipartFile file) throws Exception {
		video_BoardService.saveVideo(video,file);
		return "redirect:/video/videoList";
	}
	/**
	 * 
	 * 
	 * 수정
	 */
	@GetMapping("/video/videoUpdate/{id}")
	public String videoWrite2(@PathVariable("id") Long id,Model model) {
		Video_BoardDto video = video_BoardService.videoDetail(id);
		model.addAttribute("video", video);
		return "video/videoWrite2";
	}
	
	@PostMapping("/video/videoUpdate/{id}")
	public String videoUpdate(Video_BoardDto video, MultipartFile file) throws Exception {
		video_BoardService.updateVideo(video, file);
		return "redirect:/video/videoList";
	}
	
	
	/**
	 * 
	 * 
	 * 상세보기
	 */
	@GetMapping("/video/videoDetail")
	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
	public String videoDetail(Long id,@RequestParam String num, Model model) {
		Video_BoardDto video = video_BoardService.videoDetail(id);
		model.addAttribute("video", video);
		log.info("===================> num : " + num);
		model.addAttribute("num", num); 
		return "video/videoDetail3";
	}
	
	@GetMapping("/video/videoDelete/{id}")
	public String videoDelete(@PathVariable("id") Long id) {
		video_BoardService.videoDelete(id);
		return "redirect:/video/videoList";
	}
	 
	@GetMapping("/video/videoDetail2")
	public String videoDetail2(Long id,@ModelAttribute("requestDto") PageRequestDto requestDto, Model model ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		long UId = userService.findUserId(path);
		if(UId>0) {
			int check = ulService.checkLecture(UId,id);
			model.addAttribute("check",check);					
		}
		String nick = userService.findUserNick(path);
		model.addAttribute("nick",nick);
		
		Video_BoardDto video = video_BoardService.videoDetail(id);
		model.addAttribute("video", video);
		return "video/videoDetail2";
	}
	
}
