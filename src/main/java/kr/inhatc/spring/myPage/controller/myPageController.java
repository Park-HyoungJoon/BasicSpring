package kr.inhatc.spring.myPage.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.myPage.dto.UserLectureDTO;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.service.UserVideoService;
import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.service.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class myPageController {
	@Autowired
	UserVideoService service;
	@Autowired
	UserService userService;
	
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


	@RequestMapping("/myprofile/{id}")
	public String profile(@PathVariable("id") int id,Model model) {
		List<UserDto> user = userService.findMe(id);
		model.addAttribute("user",user);
		return "myPage/myprofile";
	}
	
	@PostMapping("/main/user/image_insert")
	public String image_insert(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile, Model model) throws Exception {
		System.out.println("너는 실행이 되는고니??");
		ClassPathResource resource = new ClassPathResource("");
		URL R = resource.getURL();
		String path = R.getPath();
		System.out.println(path+"패스입니당 ^^!");
		String upload_path = request.getSession().getServletContext().getRealPath("/"); // 프로필 사진들 모아두는 폴더
		int id = 2;
		UserDto user = userService.findByUserId(id);
		String redirect_url = "redirect:/myprofile/" + user.getId(); // 사진업로드 이후 redirect될 url

		try {
			if (user.getProfile_Photo() != null) { // 이미 프로필 사진이 있을경우
				File file = new File(upload_path + user.getProfile_Photo()); // 경로 + 유저 프로필사진 이름을 가져와서
				file.delete(); // 원래파일 삭제
			}
			mFile.transferTo(new File(path + mFile.getOriginalFilename()));  // 경로에 업로드
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		userService.img_update(id, mFile.getOriginalFilename()); // 프로필 사진이름 db에 update
		return redirect_url;
	}
	@PostMapping("/profile/update")
	public String ProfileUpdate(@RequestParam(value = "Nick",required = false) String Nick,@RequestParam(value = "self",required = false) String self,
			@RequestParam(value = "PW",required = false) String PW,Model model) throws Exception {
		System.out.println(Nick+"/"+self+"/"+PW);
		int id = 2;
		UserDto user = userService.findByUserId(id);
		
		if(PW.equals("") || PW==null) {
			String redirect_url = "redirect:/myprofile/" + user.getId();
			return redirect_url;
		}else {
			userService.updateProfile(Nick,self,PW,user.getId());
			String redirect_url = "redirect:/myprofile/" + user.getId();
			return redirect_url;
			
		}
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
