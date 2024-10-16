package com.example.demo.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class SessionAdviceHandler {
	
	// session???ㅽ봽留??꾨젅?꾩썙?ъ뿉???꾩뿭?쇰줈 愿由ы븯寃??쒕떎.
	@ModelAttribute
	public void addSessionAttributes(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		// ?몄뀡 ?뺣낫 泥댄겕 ???몄뀡???덉쑝硫?model??attribute 硫붿꽌?쒕? ?댁슜?댁꽌 ?ｌ뼱以??
		if (session != null) {
			MemberVO userInfo = (MemberVO) session.getAttribute("userInfo");
			model.addAttribute("userInfo", userInfo);
		}
	}
	
}
