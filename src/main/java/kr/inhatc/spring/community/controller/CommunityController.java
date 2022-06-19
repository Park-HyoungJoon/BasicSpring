package kr.inhatc.spring.community.controller;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.inhatc.spring.community.dto.data;
import kr.inhatc.spring.community.service.CommunityService;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.service.UserVideoService;
import kr.inhatc.spring.user.service.UserService;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableAsync
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

	@GetMapping("/com/comDetail")
	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
	public String comDetail(int id, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
		//세션을 통해 현재 유저 정보 가져옴
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int uId = userService.findUserId(path);
		UserVideoDTO video = uvService.uvDetail(id);
		try {
		int heart = cService.findHeart(uId,id);//uId는 유저, id는 UVId
		model.addAttribute("heart",heart);
		}catch(Exception e){
			
		}finally {
		model.addAttribute("video", video);
		
		return "community/comDetail";
		}
		}
	
	@PostMapping("/community/comheart")
	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
	public void comheart(data data) {
		//세션을 통해 현재 유저 정보 가져옴
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int heart = data.getHeart();
		int id = userService.findUserId(path);
		int catchId = cService.findUser(id,data.getId());
		System.out.println("catchiD : : : : "+catchId);
		
		if(catchId>0) {
			if(heart==1) {
				cService.updateHeart(id,data.getId(),heart);
			}else {
				heart=0;
				cService.updateHeart(id,data.getId(),heart);
			}
		}
		else {
			cService.addfav(id,data.getId());
		}
	}
}
