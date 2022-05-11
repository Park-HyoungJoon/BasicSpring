package kr.inhatc.spring.user_video.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inhatc.spring.user_video.dto.User_VideoDTO;
import kr.inhatc.spring.user_video.service.User_VideoService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class User_VideoController {

	@Autowired
	private User_VideoService uvService;
	
	@GetMapping("/board")
	public String board(@RequestParam(required=false) int id,Model model) {
		List<User_VideoDTO> list = uvService.userList(id);
		log.info(list.get(0).getPlayTime());
		model.addAttribute("list",list);
		return "dashboard/jQuery-plugin-progressbar";
		}
}
