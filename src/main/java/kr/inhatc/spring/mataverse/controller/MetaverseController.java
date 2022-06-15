//package kr.inhatc.spring.mataverse.controller;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import kr.inhatc.spring.mataverse.dto.MetaverseDTO;
//import kr.inhatc.spring.mataverse.service.MetaverseService;
//
//@Controller
//public class MetaverseController{
//
//	
////	@Autowired
////	MetaverseService metaverseService;
//	
////	메타버스 메인 창
//	@RequestMapping("/metaverse")
//	public String metabusMain() {
//		return "metaverse/metaverseMain";
//	}
//	
////	메타버스 수업
//	@RequestMapping("/metaverseStudy")
//	public void metarverseStudy(HttpServletResponse httpServletResponse) throws IOException {
//		httpServletResponse.sendRedirect("https://app.gather.town/app/Jbil2l6iorarf1T4/My%20E-do%20Class");
//	}
//	
//	
////	@GetMapping("/metaverseList")
////	public String mataverList(@RequestParam(required = false, defaultValue = "") String search, Model model, @PageableDefault(size = 8, sort = "M_Id", direction = Sort.Direction.DESC) Pageable pageable) {
////		Page<MetaverseDTO> list = metaverseService.metaverseList(pageable);
////		int totalPage = list.getTotalPages();
////	
////		model.addAttribute("list", list);
////		model.addAttribute("totalPage", totalPage);
////		return "/metaverseList";
////	}
//
//	
//}
