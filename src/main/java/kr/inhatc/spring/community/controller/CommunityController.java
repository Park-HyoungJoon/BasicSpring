package kr.inhatc.spring.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.inhatc.spring.community.service.CommunityService;
import kr.inhatc.spring.user.service.UserService;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CommunityController {
	@Autowired
	UserService userService;
	
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

}
