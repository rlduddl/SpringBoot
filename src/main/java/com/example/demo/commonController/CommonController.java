package com.example.demo.commonController;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	// 濡쒓렇???섏씠吏 - 異뷀썑 援ы쁽
	@RequestMapping("/login")
	public ModelAndView login(
			@RequestParam(value = "userID", required = false, defaultValue = "") String userID,
			@RequestParam(value = "password", required = false, defaultValue = "") String password
			) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		mav.addObject("title", "濡쒓렇???섏씠吏");
		mav.addObject("userID", userID);
		mav.addObject("password", password);
		return mav;
	}
	
	@PostMapping("/loginProc")
	public String loginProc(
			@ModelAttribute MemberVO memberVO,
			Model model,
			HttpServletRequest request
			//@RequestParam(value = "userID", required = true, defaultValue = "") String userID,
			//@RequestParam(value = "password", required = true, defaultValue = "") String password
			) {
		//log.info("?꾩씠??: " + userID);
		//log.info("鍮꾨쾲 : " + password);
		MemberVO result = memberService.selectOne(memberVO);
		// log.info(result.toString());
		if (result != null) {
			// ?몄뀡 遺??
			log.info("濡쒓렇???깃났");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", result);
			return "redirect:/board/list";
		} else {
			// 濡쒓렇???ㅽ뙣
			log.info("濡쒓렇???ㅽ뙣");
			
			// redirect : 吏?뺥븳 URL濡??뚮씪誘명꽣瑜??ы븿?댁꽌 GET 諛⑹떇?쇰줈 ?몄텧?쒕떎.
			// mav.setViewName("redirect:/member/login?userID=" + memberVO.getUserID() + "&password=" + memberVO.getPassword());
			// forward : 吏?뺥븳 URL濡??뚮씪誘명꽣瑜??ы븿?댁꽌 GET ?먮뒗 POST 諛⑹떇?쇰줈 ?몄텧?쒕떎.
		}
		
		return "forward:/member/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// ?몄뀡 ??젣
		session.invalidate();
		return "redirect:/";
	}
	
	/**
	 * ?뚯썝媛???묒떇
	 * @return
	 */
	@GetMapping("/join")
	public ModelAndView join() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/joinForm");
		return mav;
	}
	
	/**
	 * ?뚯썝媛??泥섎━
	 * @param memberVO
	 */
	@PostMapping("/joinProc")
	public void joinProc(@ModelAttribute MemberVO memberVO) {
		memberService.insert(memberVO);
	}
	
	/**
	 * ?뚯썝媛??鍮꾨룞湲?泥섎━
	 * @param joinRequest
	 * @RequestBody ?대끂?뚯씠?섏씠 ?덉뼱??post ?뺤떇???곗씠?곕? 諛쏆쓣 ???덈떎.
	 * @return
	 */
	@PostMapping("/joinProc2")
	@ResponseBody
	public ResponseEntity<?> joinProc2(
			@RequestBody JoinRequest joinRequest
			) {
		
		log.info(joinRequest.toString());
		// HashMap<String, Object> result = memberService.memberJoin(joinRequest);
		// return ResponseEntity.ok(result);
		return ResponseEntity.ok(memberService.memberJoin(joinRequest));
	}
	
	/**
	 * ?뚯썝?뺣낫?섏젙
	 * @param memberVO
	 */
	@PostMapping("/updateProc")
	public void updateProc(@ModelAttribute MemberVO memberVO) {
		memberService.update(memberVO);
	}
	
	/**
	 * ?뚯썝 ??젣
	 * @param memberVO
	 */
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
	
	/**
	 * 鍮꾨룞湲??듭떊 ?꾩씠??以묐났 ?뺤씤
	 * @return
	 */
	@GetMapping("/checkUserID/{userID}")
	@ResponseBody
	public ResponseEntity<?> checkUserID(
			@PathVariable String userID
			) {
		HashMap<String, Object> result = memberService.checkUserID(userID);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 鍮꾨룞湲??듭떊 ?대찓??以묐났 ?뺤씤
	 * @param email
	 * @return
	 */
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
	
	/**
	 * ?대찓?쇰줈 ?꾩씠??李얘린
	 * @param email
	 * @return
	 */
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
	
	
	/**
	 * ?ъ슜???뺣낫 ?섏젙 ?섏씠吏
	 * @param request
	 * @return
	 */
	@RequestMapping("/myInfo")
	public ModelAndView myInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/myInfo");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("userInfo");
		
		mav.addObject("userInfo", memberVO);
		
		return mav;
	}
	
	/**
	 * ?뚯썝?뺣낫 ?섏젙
	 * @param request
	 * @param memberVO
	 * @return
	 */
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
