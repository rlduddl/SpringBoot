package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DefaultController {

	// @RequestMapping("/") : GET, POST, DELETE, PUT, UPDATE
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		MemberVO memberInfo = (MemberVO) session.getAttribute("userInfo");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberInfo", memberInfo);
		// jsp 파일 지정
		mav.setViewName("common/index");
		// jsp 파일에서 사용할 데이터 오브젝트
		mav.addObject("title", "인덱스 페이지");
		
		return mav;
	}
	
}
