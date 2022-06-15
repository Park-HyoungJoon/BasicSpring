package kr.inhatc.spring.mataverse.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MetaverseController{

	
//	메타버스 메인 창
	@RequestMapping("/metaverse")
	public String metabusMain() {
		return "metaverse/metaverseMain";
	}
	
//	메타버스 수업
	@RequestMapping("/metaverseStudy")
	public void metarverseStudy(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.sendRedirect("https://app.gather.town/app/Jbil2l6iorarf1T4/My%20E-do%20Class");
	}
	

	
}
