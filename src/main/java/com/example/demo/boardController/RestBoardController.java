package com.example.demo.boardController;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.service.impl.BoardServiceImpl;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// RESTful API
// rest : ?몄븞?섎떎 x
// Representational State Transfer
// ?먯썝(Resource) : REST?먯꽌 紐⑤뱺 寃껋? ?먯썝?쇰줈 ?쒗쁽?쒕떎.
//		?먯썝? URI(Uniform Resource Identifier)瑜??듯빐 怨좎쑀?섍쾶 ?앸퀎?쒕떎. (??: ?ъ슜???뺣낫瑜??대엺 /member/user?idx=1 
//		http://?꾨찓??member/user/1
// URI ,URL
// URL : Uniform Resource Locator (??: ?뱀젙???먯썝???꾩튂瑜??섑??몃떎.) http://?꾨찓??member/user/info.html
// ?곹깭 ?꾩씠(Stateless) : RESTful API???곹깭媛 ?녿떎. 媛??붿껌? ?낅┰?곸씠怨? ?대씪?댁뼵?몄쓽 ?곹깭瑜??쒕쾭媛 ??ν븯吏 ?딅뒗?? ?대씪?댁뼵?몃뒗 ?꾩슂???뺣낫瑜??붿껌???ы븿?댁빞?쒕떎.
// ?쒗쁽(Representation) : ?대씪?댁뼵?멸? ?먯썝???쒗쁽???붿껌?섍퀬, ?쒕쾭??JSON, XML ?뺤떇?쇰줈 諛섑솚?쒕떎.
// HTTP 硫붿꽌??: GET, POST (?쇰컲?곸씤 request?뺤떇)
//		GET : ?먯썝 ?붿껌
//		POST : ?덈줈???먯썝 ?앹꽦
// 		PUT : ?먯썝 ?섏젙 (?꾩껜)
//		PATCH : ?먯썝 ?섏젙 (遺遺?
//		DELETE : ?먯썝 ??젣


@RestController
@RequestMapping("/api/board")
@Slf4j
@AllArgsConstructor
public class RestBoardController {
	
	private BoardServiceImpl boardService;
	
	/**
	 * 寃뚯떆臾????
	 * @param boardVO
	 * @return
	 */
	@PostMapping("/insert")
	public ResponseEntity<?> insertBoard(
			@RequestBody BoardVO boardVO, HttpServletRequest request, Model model
			){
		MemberVO memberVO = (MemberVO) model.getAttribute("userInfo");
		boardVO.setRegID(memberVO.getUserID());
		return ResponseEntity.ok(boardService.insertBoard(boardVO, request));
	}
	
	/**
	 * 寃뚯떆臾?紐⑸줉
	 * @param boardVO
	 * @return
	 */
	@GetMapping("/list")
	public ResponseEntity<?> boardList(@ModelAttribute BoardVO boardVO) {
		return ResponseEntity.ok(boardService.selectList(boardVO));
	}
	
	/**
	 * 寃뚯떆臾??대엺
	 * @param idx
	 * @return
	 */
	@GetMapping("/view/{idx}")
	public ResponseEntity<?> boardView(@PathVariable Long idx) {
		return ResponseEntity.ok(boardService.selectBoard(idx));
	}
	
	/**
	 * 寃뚯떆臾??섏젙
	 * @param boardVO
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> boardUpdate(@RequestBody BoardVO boardVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO memberVO  = (MemberVO) session.getAttribute("userInfo");
		boardVO.setRegID(memberVO.getUserID());
		return ResponseEntity.ok(boardService.updateBoard(boardVO));
	}
	
	/**
	 * 寃뚯떆臾???젣
	 * @param idx
	 * @return
	 */
	@DeleteMapping("/delete/{idx}")
	public ResponseEntity<?> boardDelete(@PathVariable Long idx) {
		boardService.delete(idx);
		return ResponseEntity.ok(new ApiResponse(true, "??젣?섏뿀?듬땲??"));
	}
	

}
