package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dir")
// 濡щ났?쇰줈 濡쒓렇 ?뺤씤?섎뒗 ?대끂?뚯씠??
// ??: log.debug("?댁슜");
// @Slf4j
public class DefaultController {
	
	// @Slf4j 瑜??ъ슜?섏? ?딅뒗 ?쇰컲?곸씤 濡쒓렇 異쒕젰 留대쾭 蹂???ㅼ젙
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 湲곕낯?곸씤 jsp 由ы꽩
	 * @return
	 */
	@GetMapping("/path1")
	public String path1() {
		// jsp?뺤옣?먮뒗 ?앸왂
		log.info("sfdsfd");
		return "html(jsp)?뚯씪 寃쎈줈";
	}
	
	/**
	 * ModelAndView??jsp? ?곗씠?곕? ?④퍡 由ы꽩
	 * @return
	 */
	@GetMapping("/path2")
	public ModelAndView path2() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("key?대쫫", "key??媛믪씠 ?ㅼ뼱媛꾨떎(int, string, Object)");
		mav.setViewName("html(jsp)?뚯씪 寃쎈줈");
		return mav;
	}
	
	/**
	 * 由ы꽩??낆씠 ?ㅽ듃留곸씪 寃쎌슦 ?곗씠???ㅻ툕?앺듃瑜??ｋ뒗踰?
	 * @param model
	 * @return
	 */
	@GetMapping("/path3")
	public String path3(Model model) {
		model.addAttribute("key?대쫫", "key??媛믪씠 ?ㅼ뼱媛꾨떎(int, string, Object)");
		return "html(jsp)?뚯씪 寃쎈줈";
	}
	
	/**
	 * GET?먮뒗 POST濡??뚮씪誘명꽣 諛쏅뒗踰?1
	 * @param model
	 * @param value1
	 * @return
	 */
	@GetMapping("/path4")
	public String path4(
			Model model,
			@RequestParam(value = "key紐?, required = true, defaultValue = "湲곕낯媛?) String value1
			) {
		model.addAttribute("key?대쫫", "key??媛믪씠 ?ㅼ뼱媛꾨떎(int, string, Object)");
		return "html(jsp)?뚯씪 寃쎈줈";
	}
	
	/**
	 * GET?먮뒗 POST濡??뚮씪誘명꽣 諛쏅뒗踰?2
	 * @param memberVO
	 * @return
	 */
	@GetMapping("/path5")
	public String path5(@ModelAttribute MemberVO memberVO) {
		return "html(jsp)?뚯씪 寃쎈줈";
	}
	
	/**
	 * ?곗궛 ??由щ떎?대젆???먮뒗 ?ъ썙??
	 * 由щ떎?대젆?몄? ?ъ썙??紐⑤몢 URL???대룞?섎뒗 湲곕뒫
	 * redirect : ?⑥닚 URL?대룞 (?뚮씪誘명꽣 ?ㅼ젙媛??
	 * forward : ?뚮씪誘명꽣瑜?湲곕낯?곸쑝濡?紐⑥“由??ы븿?댁꽌 URL ?대룞
	 * @return
	 */
	@GetMapping("/path6")
	public ModelAndView path6(@ModelAttribute MemberVO memberVO) {
		ModelAndView mav = new ModelAndView();
		
		// 留뚯빟 ?뚮씪誘명꽣濡?諛쏆? ?곗씠?곌? ?덉쓣 寃쎌슦 由щ떎?대젆?몃뒗 ?뚮씪誘명꽣瑜?肄붾뱶濡?紐⑤몢 ?묒꽦?댁쨾???쒕떎.
		mav.setViewName("redirect:/member/login?userID=" + memberVO.getUserID());
		// 留뚯빟?뚮씪誘명꽣濡?諛쏆? ?곗씠?곌? ?덉쓣 寃쎌슦 ?ъ썙?쒕뒗 ?뚮씪誘명꽣媛 ?먮룞?쇰줈 遺숇뒗??
		mav.setViewName("forward:/member/login");
		
		return mav;
	}
	
	/**
	 * @PostMapping? ?ъ슜?먭? ?묒꽦???쇱쓣 URL???뚮씪誘명꽣濡??ы븿?섏? ?딅뒗?? 
	 * @param memberVO
	 * @return
	 */
	@PostMapping("/path7")
	public String path7(@ModelAttribute MemberVO memberVO) {
		// 臾댁뼵媛 ?곗궛 ?섑뻾 ??由щ떎?대젆??
		return "redirect:/member/list";
	}
	
	/**
	 * URL??GET, POST 紐⑤몢 ?ъ슜??寃쎌슦 @RequestMapping ?ъ슜
	 * @return
	 */
	@RequestMapping("/path8")
	public String path8() {
		return "html(jsp)?뚯씪 寃쎈줈";
	}
	
	
}
