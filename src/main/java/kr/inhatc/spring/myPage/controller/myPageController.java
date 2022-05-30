package kr.inhatc.spring.myPage.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.login.entity.Member;
import kr.inhatc.spring.myPage.dto.UserLectureDTO;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.service.UserFriendService;
import kr.inhatc.spring.myPage.service.UserVideoService;
import kr.inhatc.spring.user.dto.UserDto;
import kr.inhatc.spring.user.entity.User;
import kr.inhatc.spring.user.service.UserService;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.service.Video_BoardService;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class myPageController{
	@Autowired
	UserVideoService service;
	@Autowired
	UserService userService;
	@Autowired
	UserFriendService ufservice;
	 

	@RequestMapping("/mypage")
	public String myPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		List<UserDto> udt = userService.findAllData(path);
		List<UserLectureDTO> ulDTO = service.latestuLectureList(udt.get(0).getId());
		List<UserVideoDTO> uvDTO = service.latestuVideoList(udt.get(0).getId());
		model.addAttribute("data", udt);
		model.addAttribute("ulList", ulDTO);
		model.addAttribute("uvList", uvDTO);

		return "myPage/mypage";
	}

	@RequestMapping(value = "/myLecture", method = RequestMethod.GET)
	public String myLecture(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		List<UserLectureDTO> list = service.uLectureList(id);
		model.addAttribute("list", list);
		return "myPage/myLecture";
	}

	@RequestMapping(value = "/myVideo", method = RequestMethod.GET)
	public String myVideo(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		System.out.println(id);
		List<UserVideoDTO> list = service.videoList(id);
		model.addAttribute("list", list);
		return "myPage/myVideo";
	}

	/*
	 * @RequestMapping(value="/user/userList", method=RequestMethod.GET) public
	 * String userList(Model model) { List<BasicDto> list = userService.userList();
	 * model.addAttribute("list", list); return "user/userList"; }
	 */
	
	@RequestMapping("/friendList")
	public String friendList(Model model) {

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String path = auth.getName();
			int id = userService.findUserId(path);
			String result = ufservice.findMyFriend(id);
			List<UserDto> result2 = userService.findFriend(id);
			System.out.println(result2.get(0).getNick());
			System.out.println(result);
//		  JSONParser parser = new JSONParser();
//			  JSONObject jsonobj = (JSONObject) parser.parse(result); 
//			  model.addAttribute("result",jsonobj);
			model.addAttribute("Friend", result);
			model.addAttribute("AllFriend", result2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "myPage/friendList";
	}

	@RequestMapping("/myprofile")
	public String profile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		List<UserDto> dto = userService.findMe(id);
		model.addAttribute("user", dto);
		return "myPage/myprofile";
	}

	@PostMapping("/main/user/image_insert")
	public String image_insert(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile, Model model)
			throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		ClassPathResource resource = new ClassPathResource("");
		URL R = resource.getURL();
		String path2 = R.getPath();
		System.out.println(path + "패스입니당 ^^!");
		String upload_path = request.getSession().getServletContext().getRealPath("/"); // 프로필 사진들 모아두는 폴더
		UserDto user = userService.findByUserId(id);
		String redirect_url = "redirect:/myprofile/"; // 사진업로드 이후 redirect될 url

		try {
			if (user.getProfile_Photo() != null) { // 이미 프로필 사진이 있을경우
				File file = new File(upload_path + user.getProfile_Photo()); // 경로 + 유저 프로필사진 이름을 가져와서
				file.delete(); // 원래파일 삭제
			}
			System.out.println("????"+upload_path );
			System.out.println(path + mFile.getOriginalFilename());
			mFile.transferTo(new File(path + mFile.getOriginalFilename())); // 경로에 업로드
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		userService.img_update(id, mFile.getOriginalFilename()); // 프로필 사진이름 db에 update
		return redirect_url;
	}

	@PostMapping("/profile/update")
	public String ProfileUpdate(@RequestParam(value = "Nick", required = false) String Nick,
			@RequestParam(value = "self", required = false) String self, Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int UId = userService.findUserId(path);
		userService.updateProfile(Nick, self, UId);
		String redirect_url = "redirect:/myprofile/";
		return redirect_url;
	}

	@PostMapping("/profile/delete")
	public String ProfileDelete() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		userService.deleteUser(id);
		return "redirect:/logout";
	}

	@GetMapping("/myPage/UVwrite")
	public String videoWrite(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		List<UserVideoDTO> list = service.videoList(id);
		model.addAttribute("list", list);
		return "myPage/UVwrite";
	}

	@GetMapping("/addFriend/{id}")
	public String addFriend(@PathVariable("id") int id, Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int UId = userService.findUserId(path);
		ufservice.addFriend(id, UId);
		String redirect_url = "redirect:/friendList";
		return redirect_url;
	}

	@GetMapping("/addLecture/{id}")
	public String addLecture(@PathVariable("id") int id, Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int UId = userService.findUserId(path);
		service.addLecture(id, UId);		
		List<UserLectureDTO> list = service.uLectureList(id);
		model.addAttribute("list", list);
		String redirect_url = "redirect:/myLecture";
		return redirect_url;
	}
	@PostMapping("/myPage/UVtoss")
	public String videoToss(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "contents", required = false) String contents, Model model) {
		System.out.println(title + ",,," + contents);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int id = userService.findUserId(path);
		service.saveVideo(title, contents, id);
		return "redirect:/myVideo";
	}

	@GetMapping("/videoDelete/{id}")
	// Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
	// 그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
	public String videoDetail(@PathVariable("id") Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		int UId = userService.findUserId(path);
		Long UVId = id;
		service.deleteUV(UId, UVId);
		return "redirect:/myVideo";
	}
	
@GetMapping("/LectureDelete/{id}")
// Rest방식 /user/Detail/13 이렇게 경로처럼 받으면 Pathvariable 써야함,,
// 그냥 일반 파라미터 값 /board/Detail?boardIdx=3 이런식으로 받으면 @RequestPram으로 쓰고
public String LectureDetail(@PathVariable("id") Long id) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String path = auth.getName();
	int UId = userService.findUserId(path);
	Long ULPId = id;
	service.deleteUL(UId, ULPId);
	return "redirect:/myLecture";
}

}