package kr.inhatc.spring.myPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.inhatc.spring.myPage.dto.UserLectureDTO;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.service.UserVideoService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class myPageController {
	@Autowired
	UserVideoService service;
	
	@RequestMapping("/mypage")
	public String myPage() {
		return "myPage/mypage";
	}

	@RequestMapping(value="/myLecture",method=RequestMethod.GET)
	public String myLecture(Model model) {
		List<UserLectureDTO> list = service.uLectureList();
		model.addAttribute("list",list);
		return "myPage/myLecture";
	}

	@RequestMapping(value="/myVideo", method=RequestMethod.GET)
	public String myVideo(Model model) {
		List<UserVideoDTO> list = service.videoList();
		model.addAttribute("list",list);
		return "myPage/myVideo";
	}

	/*
	 * @RequestMapping(value="/user/userList", method=RequestMethod.GET) public
	 * String userList(Model model) { List<BasicDto> list = userService.userList();
	 * model.addAttribute("list", list); return "user/userList"; }
	 */
	
	@RequestMapping("/friendList")
	public String friendList() {
		return "myPage/friendList";
	}


	@RequestMapping("/myprofile")
	public String profile() {
		return "myPage/myprofile";
	}
//	// GET(read), POST(create), PUT(update), DELETE(delete)
//	@RequestMapping(value="/user/userList", method=RequestMethod.GET)
//	public String userList(Model model) {
//		List<BasicDto> list = userService.userList();
//		model.addAttribute("list", list);
//		return "user/userList";
//	}
//	
//	@GetMapping("/user/userInsert")
//	public String userWrite() {
//		return "user/userWrite";
//	}
//	
//	// 원래 엔티티 클래스는 요청이나 응답에 사용되면 안되는데 일단은 영상 그대로 따라하고 나중에 Request, Response 만들어보자
//	@PostMapping("/user/userInsert")
//	public String userInsert(BasicDto user) {
//		userService.saveUser(user);
//		return "redirect:/user/userList";
//	}
//	
//	@GetMapping("/user/userDetail/{id}")
//	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
//	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
//	public String userDetail(@PathVariable("id") Long id, Model model) {
//		BasicDto user = userService.userDetail(id);
//		model.addAttribute("user", user);
//		return "user/userDetail";
//	}
//	
//	
//	@GetMapping("/user/userDelete/{id}")
//	public String userDelete(@PathVariable("id") Long id) {
//		userService.userDelete(id);
//		return "redirect:/user/userList";
//	}
	
}
