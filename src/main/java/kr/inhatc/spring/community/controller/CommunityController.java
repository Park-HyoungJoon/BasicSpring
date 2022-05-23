package kr.inhatc.spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.inhatc.spring.community.service.CommunityService;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.service.UserVideoService;
import kr.inhatc.spring.user.service.UserService;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CommunityController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserVideoService uvService;
	
	@Autowired
	CommunityService cService;
	
	@GetMapping(value="/community/comList")
	public String videoList(PageRequestDto pageRequestDto, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		model.addAttribute("UserId",id);
		model.addAttribute("list", cService.getList(pageRequestDto));
		return "community/comList";
	}

//	@GetMapping("/community/comDetail")
//	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
//	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
//	public String comDetail(Long id, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
//		UserVideoDTO video = uvService.uvDetail(id);
//		model.addAttribute("video", video);
//		return "community/comDetail";
//	}
}
