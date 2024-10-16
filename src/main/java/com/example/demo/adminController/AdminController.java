package com.example.demo.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.impl.MemberServiceImpl;
import com.example.demo.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	//public AdminController(MemberService memberService) {
	//	this.memberService = memberService;
	//}

	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/dashboard");
		return mav;
	}
	
	// memberList?userID=abcd
	// http://localhost:8080/admin/memberList?userID=abcd
	// @RequestParam ?대끂?뚯씠?섏쑝濡??뚮씪誘명꽣瑜??ｋ뒗??
	// @RequestParam value??url???ㅺ컪, required???꾩닔 ?щ?, defaultValue??媛믪씠 ?놁쓣???ъ슜??湲곕낯媛?
	@GetMapping("/memberList")
	public ModelAndView memberList(
			
			@ModelAttribute MemberVO memberVO
			
			
			//@RequestParam(value = "userID", required = false, defaultValue = "") String userID,
			//@RequestParam(value = "password", required = false, defaultValue = "") String password
	) {
		
		log.info("=====================");
		log.info(memberVO.toString());
		log.info("=====================");
		
		// TRACE : 媛???몃???濡쒓렇 - 硫붿냼?쒖쓽 ?쒖옉, ?? 蹂?섏긽?? 留ㅺ컻蹂???깅벑
		// DEBUG : ?대? ?곹솴 異붿쿃
		// INFO : ?쇰컲?곸씤 ?뺣낫 異쒕젰
		// WARN : 寃쎄퀬 濡쒓렇 異쒕젰 (?ν썑 臾몄젣媛 諛쒖깮??媛?μ꽦???덈뒗 寃쎌슦 異쒕젰)
		// ERROR : 移섎챸???ㅻ쪟媛 諛쒖깮?덉쓣??異쒕젰 *******
		
		List<MemberVO> list = memberService.selectList(memberVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/memberList");
		mav.addObject("title", "?뚯썝紐⑸줉");
		mav.addObject("list", list);
		return mav;
	}
	
}
