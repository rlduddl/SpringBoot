package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. ?몄뀡 泥댄겕
		HttpSession session = request.getSession(false);
		// 2. 泥댄겕?댁꽌 ?몄뀡 媛믪씠 ?덉쑝硫?濡쒓렇???쒓굅??
		if (session != null) {
			MemberVO userInfo = (MemberVO) session.getAttribute("userInfo");
			if (userInfo != null) return true;
		}
		// 3. ?몄뀡 媛믪씠 ?놁쑝硫?null 濡쒓렇???섏씠吏濡??대룞
		response.sendRedirect(request.getContextPath() + "/member/login");
		return false;
	}

}
