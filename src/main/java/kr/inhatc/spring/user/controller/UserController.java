package kr.inhatc.spring.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.service.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String hello() {
		return "redirect:/user/userList";
	}
	
	// GET(read), POST(create), PUT(update), DELETE(delete)
	@RequestMapping(value="/user/userList", method=RequestMethod.GET)
	public String userList(Model model) {
		List<UserDto> list = userService.userList();
		model.addAttribute("list", list);
		return "user/userList";
	}
	
	@GetMapping("/user/userInsert")
	public String userWrite() {
		return "user/userWrite";
	}
	
	// 원래 엔티티 클래스는 요청이나 응답에 사용되면 안되는데 일단은 영상 그대로 따라하고 나중에 Request, Response 만들어보자
	@PostMapping("/user/userInsert")
	public String userInsert(UserDto user) {
		userService.saveUser(user);
		return "redirect:/user/userList";
	}
	
	@GetMapping("/user/userDetail/{id}")
	//  Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
	//  그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
	public String userDetail(@PathVariable("id") Long id, Model model) {
		UserDto user = userService.userDetail(id);
		model.addAttribute("user", user);
		return "user/userDetail";
	}
	
	
	@GetMapping("/user/userDelete/{id}")
	public String userDelete(@PathVariable("id") Long id) {
		userService.userDelete(id);
		return "redirect:/user/userList";
	}
	
}
