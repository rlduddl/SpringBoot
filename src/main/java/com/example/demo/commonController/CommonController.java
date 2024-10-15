package com.example.demo.commonController;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.payload.request.ChangePwRequest;
import com.example.demo.payload.request.JoinRequest;
import com.example.demo.service.impl.MemberServiceImpl;
import com.example.demo.util.StringUtil;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class CommonController {

	@Autowired
	private MemberServiceImpl memberService;
	
	// 로그인 페이지 - 추후 구현
	@RequestMapping("/login")
	public ModelAndView login(
			@RequestParam(value = "userID", required = false, defaultValue = "") String userID,
			@RequestParam(value = "password", required = false, defaultValue = "") String password
			) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/login");  // 충돌 해결
		mav.addObject("title", "로그인 페이지");
		mav.addObject("userID", userID);
		mav.addObject("password", password);
		return mav;
	}
	
	@PostMapping("/loginProc")
	public ModelAndView loginProc(
			@ModelAttribute MemberVO memberVO,
			HttpServletRequest request
			) {
		ModelAndView mav = new ModelAndView();
		MemberVO result = memberService.selectOne(memberVO);
		if (result != null) {
			log.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", result);
			mav.setViewName("redirect:/");
		} else {
			log.info("로그인 실패");
			mav.setViewName("forward:/member/login");
		}
		
		return mav;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/join")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/joinForm");
		return mav;
	}
	
	@PostMapping("/joinProc")
	public void joinProc(@ModelAttribute MemberVO memberVO) {
		memberService.insert(memberVO);
	}
	
	@PostMapping("/joinProc2")
	@ResponseBody
	public ResponseEntity<?> joinProc2(
			@RequestBody JoinRequest joinRequest
			) {
		log.info(joinRequest.toString());
		return ResponseEntity.ok(memberService.memberJoin(joinRequest));
	}
	
	@PostMapping("/updateProc")
	public void updateProc(@ModelAttribute MemberVO memberVO) {
		memberService.update(memberVO);
	}
	
	@PostMapping("/deleteProc")
	public void deleteProc(@ModelAttribute MemberVO memberVO) {
		memberService.delete(memberVO);
	}
	
	@GetMapping("/memberDrop")
	public void memberDrop(
			@RequestParam(value = "idx", required = true, defaultValue = "0") Long idx
			) {
		memberService.memberDrop(idx);
	}
	
	@GetMapping("/checkUserID/{userID}")
	@ResponseBody
	public ResponseEntity<?> checkUserID(
			@PathVariable String userID
			) {
		HashMap<String, Object> result = memberService.checkUserID(userID);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/checkEmail/{email}")
	@ResponseBody
	public ResponseEntity<?> checkEmail(
			@PathVariable String email
			) {
		HashMap<String, Object> result = memberService.checkEmail(email);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findID")
	public String findID() {
		return "common/findID";
	}
	
	@GetMapping("/findID/{email}")
	@ResponseBody
	public ResponseEntity<?> findIDByEmail(@PathVariable String email) {
		return ResponseEntity.ok(memberService.findID(email));
	}
	
	@GetMapping("/findPW")
	public String findPW() {
		return "common/findPW";
	}
	
	@PostMapping("/changePW")
	@ResponseBody
	public ResponseEntity<?> changePW(
			@RequestBody ChangePwRequest changePwRequest) {
		return ResponseEntity.ok(memberService.changePW(changePwRequest));
	}
	
	@PostMapping("/test")
	@ResponseBody
	public ResponseEntity<?> test(
			@RequestBody HashMap<String, Object> map) {
		log.info(map.toString());
		StringUtil.printMap("test", map);
		return ResponseEntity.ok("sdfsdfsdffds");
	}
	
	@RequestMapping("/myInfo")
	public ModelAndView myInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/myInfo");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("userInfo");
		mav.addObject("userInfo", memberVO);
		
		return mav;
	}
	
	@PostMapping("/updateInfo")
	public ModelAndView updateInfo(
			HttpServletRequest request,
			@ModelAttribute MemberVO memberVO
			) {
		log.info(memberVO.toString());
		
		boolean result = memberService.updateInfo(request, memberVO);
		ModelAndView mav = new ModelAndView();
		
		if (result) {
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("forward:/member/myInfo");
		}
		
		return mav;
	}
}
